package base.controller;

import java.util.Optional;

import base.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import base.model.dto.UserDto;
import base.model.dto.UserProfile;
import base.model.payload.ApiResponse;
import base.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/user-management")
public class UserController {

	@Autowired
	private UserService userService;

	// change user role
	@PostMapping("/user/change-role/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<?> changeUserRole(@PathVariable Integer id, @RequestBody UserDto requestUser) {
		userService.updateUserRole(id, requestUser);

		return ResponseEntity.ok(new ApiResponse(true, "Update user role success"));
	}

	// get user profile
	@GetMapping("/user/profile/{username}")
	 @Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public UserProfile getUserProfile(@PathVariable String username) {
		UserProfile userProfile = userService.getUserProfile(username);

		return userProfile;

	}

	// update user profile
	@PutMapping("/user/update-profile/{username}")
	 @Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<?> updateProfile(@PathVariable String username, @RequestBody UserProfile requestUser) {
		userService.updateUserProfile(username, requestUser);

		return ResponseEntity.ok(new ApiResponse(true, "Update information success"));
	}

	@PutMapping("/account/password")
	 @Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<?> changePassword(@PathVariable String username, @RequestBody UserProfile requestUser) {
		userService.changePassword(username, requestUser);

		return ResponseEntity.ok(new ApiResponse(true, "Update information success"));

	}

	// find all users
	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public Page<UserDto> findAllUser(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy,
			@RequestParam Optional<String> direction) {

		return userService.findAllUsers(page, sortBy, direction);
	}

	// find user by id
	@GetMapping("/user/{id}")
	 @Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public UserDto findUserById(@PathVariable Integer id) {
		return userService.findById(id);
	}
	
	//update user status
	@PutMapping("/user/status/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	 @Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<?> updateUserStatus(@PathVariable Integer id) {
		userService.updateUserStatus(id);

		return ResponseEntity.ok(new ApiResponse(true, "Update information success"));

	}



	@PutMapping("/user/userChangePassword")
	@Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<?> userChangePassword(@RequestParam int userId,@RequestParam String oldPassword,@RequestParam String newPassword) throws Exception {
		User user = userService.userChangePassword(userId,oldPassword,newPassword);
		if(user == null){
			return ResponseEntity.ok(new ApiResponse(false, "wrong password"));
		}else{
			return ResponseEntity.ok(user);
		}
	}

}
