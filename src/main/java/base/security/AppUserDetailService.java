package base.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import base.model.entity.User;
import base.service.UserService;

/**
 * Return UserDeatail object that Spring Security uses for performing various authentication 
 * and role based validations.
 * @author Huu Bang
 *
 */
@Service
public class AppUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		//Login with username or email
		User user = userService.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		
		//return custom UserDetail
		return new AppUserDetail(user);
	}

}
