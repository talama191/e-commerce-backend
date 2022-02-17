package base.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import base.model.entity.Cart;
import base.model.entity.Role;
import base.model.entity.User;
import base.model.payload.ApiResponse;
import base.model.payload.AuthenticationResponse;
import base.model.payload.LoginRequest;
import base.model.payload.SignUpRequest;
import base.security.AppUserDetailService;
import base.security.JwtUtil;
import base.service.CartService;
import base.service.RoleService;
import base.service.UserService;

/**
 * Provide sign in and sign up API
 * 
 * @author Vi Tuan
 *
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AppUserDetailService userDetailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * @effects
	 * <pre>
	 *	if account is incorrect 
	 *		throw new exception
	 *	else
	 *		generate new Token and send back to client	
	 * </pre>
	 * @param loginRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception{
		
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		}catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getUsernameOrEmail());
		final String jwt = jwtUtil.generateToken(userDetails);
    //new AuthenticationResponse(jwt) ,
		return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt,userDetails));
	}
	
	/**
	 * @effects <pre>
	 * 	if user input is not valid
	 * 		return fail message
	 * 	else
	 * 		create new user and send success message
	 * </pre>
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){
		//Validate user name
		if(userService.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already exist"), 
					HttpStatus.BAD_REQUEST);
		}
		//Validate phone number
		if(userService.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
			return new ResponseEntity(new ApiResponse(false, "Phone number is already in user"), 
					HttpStatus.BAD_REQUEST);
		}
		
		//Validate email
		if(userService.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email is already in use"), 
					HttpStatus.BAD_REQUEST);
		}
		
		User user = userService.mapSignUpRequestToUser(signUpRequest);
		
		Role userRole = roleService.findByName("ROLE_USER");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setRoles(Collections.singleton(userRole));
		


//		user.setEnabled(true);
		
		//Create new cart for user
		
		
		User result = userService.save(user);
		Cart cart = new Cart();
		cart.setId(result.getId());
		cart.setUser(result);
		cartService.saveCart(cart);
		return ResponseEntity.ok(result);
		
	}
}
