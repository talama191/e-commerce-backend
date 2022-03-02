package base.service;

import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import base.model.payload.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import base.exception.ResourceNotFoundException;
import base.model.dto.RoleDto;
import base.model.dto.UserDto;
import base.model.dto.UserProfile;
import base.model.entity.User;
import base.model.payload.SignUpRequest;
import base.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	@Transactional
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));
		return user;
	}
	
	@Transactional
	public Page<UserDto> findAllUsers(Optional<Integer> page, 
									  Optional<String> sortBy,
									  Optional<String> direction){
		Sort sort = direction.orElse("asc").equalsIgnoreCase(Sort.Direction.ASC.name()) 
    			? Sort.by(sortBy.orElse("id")).ascending() : Sort.by(sortBy.orElse("id")).descending();
		
		Pageable pageable = PageRequest.of(page.orElse(0),
										   10,
										   sort);
		Page<UserDto> users =  userRepository.findAll(pageable).map(new Function<User, UserDto>() {
				@Override
				public UserDto apply(User u) {
					
					return mapUsertoUserDto(u);
				}
		});
		
		return users;
	}
	

	@Transactional
	public User findByUsernameOrEmail(String username, String email) {
		User user = userRepository.findByUsernameOrEmail(username, email)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));
		return user;
	}

	@Transactional
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Username", username));
		return user;
	}

	@Transactional
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Transactional
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Transactional
	public Boolean existsByPhoneNumber(String phoneNumber) {
		return userRepository.existsByPhoneNumber(phoneNumber);
	}

	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
	
	

	@Transactional
	public UserDto updateUserRole(Integer id, UserDto requestUserDto) {
		User requestUser = mapUserDtoToUser(requestUserDto);

		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User", "id", id);
		}

		UserDto userDto = userRepository.findById(id).map(u -> {
			u.setRoles(requestUser.getRoles());
			User updatedUser = userRepository.save(u);

			return mapUsertoUserDto(updatedUser);
		}).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return userDto;
	}
	
	public UserDto updateUserStatus(Integer id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User", "id", id);
		}
		
		UserDto userDto = userRepository.findById(id).map(u -> {
//			u.setEnabled(!u.getEnabled()); //Toggle user status true/false
			User updatedUser = userRepository.save(u);

			return mapUsertoUserDto(updatedUser);
		}).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return userDto;
		
	}
	
	@Transactional
	public UserProfile updateUserProfile(String username, UserProfile requestUserProfile) {
		User requestUser = modelMapper.map(requestUserProfile, User.class);

		if (!userRepository.existsByUsername(username)) {
			throw new ResourceNotFoundException("User", "username", username);
		}

		UserProfile userProfileUpdated = userRepository.findByUsername(username).map(u -> {
			u.setAddress(requestUser.getAddress());
			u.setDob(requestUser.getDob());
			u.setPhoneNumber(requestUser.getPhoneNumber());
			u.setFullname(requestUser.getFullname());
			u.setGender(requestUser.getGender());
			u.setEmail(requestUser.getEmail());
			
			User updatedUser = userRepository.save(u);
			
			return modelMapper.map(updatedUser, UserProfile.class);
			
		}).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		return userProfileUpdated;
	}
	
	@Transactional
	public UserProfile changePassword(String username, UserProfile requestUserProfile) {
		User requestUser = modelMapper.map(requestUserProfile, User.class);

		if (!userRepository.existsByUsername(username)) {
			throw new ResourceNotFoundException("User", "username", username);
		}

		UserProfile userProfileUpdated = userRepository.findByUsername(username).map(u -> {
			u.setPassword(passwordEncoder.encode(requestUser.getPassword()));
			User updatedUser = userRepository.save(u);
			
			return modelMapper.map(updatedUser, UserProfile.class);
			
		}).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		return userProfileUpdated;
	}

	@Transactional
	public UserProfile getUserProfile(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		return modelMapper.map(user, UserProfile.class);
	}

	@Transactional
	public UserDto findById(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		Set<RoleDto> roleDto = user.getRoles().stream().map(
				r -> modelMapper.map(r, RoleDto.class)).collect(Collectors.toSet());
		
		UserDto userDto = mapUsertoUserDto(user);
		userDto.setRole(roleDto);
		
		return userDto;
	}


	@Transactional
	public User userChangePassword(int userId,String oldPassword,String newPassword) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		if (!passwordEncoder.matches(oldPassword,user.getPassword())){
			return null;
		}else{
			user.setPassword(passwordEncoder.encode(newPassword));
		}

		return userRepository.save(user);

	}
	

	public User mapSignUpRequestToUser(SignUpRequest signUpRequest) {
		return modelMapper.map(signUpRequest, User.class);
	}

	private UserDto mapUsertoUserDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	private User mapUserDtoToUser(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}
}
