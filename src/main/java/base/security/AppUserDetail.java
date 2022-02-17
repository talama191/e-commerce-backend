package base.security;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import base.model.entity.Cart;
import base.model.entity.User;
import lombok.Getter;

/**
 * UserDetails object which help Spring security perform authentication and authorization
 * @author Huu Bang
 *
 */
@Getter
public class AppUserDetail implements UserDetails{
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String fullname;
	
	private String phonenNumber;
	
	@JsonIgnore
	private String email;
	
	@JsonIgnore
	private String address;
	
	private Character gender;
	
	private Date dob;
	
	private Cart cart;
	
	
	private Boolean enabled;
	
	private List<? extends GrantedAuthority> authorities;
	
	public AppUserDetail(User user) {
		this.id= user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.fullname = user.getFullname();
		this.phonenNumber = user.getPhoneNumber();
		this.email = user.getEmail();
		this.address = user.getAddress();
		this.gender = user.getGender();
		this.dob = user.getDob();
//		this.enabled = user.getEnabled();
		this.authorities =  user.getRoles().stream()
											.map(role -> new SimpleGrantedAuthority(role.getName()))
											.collect(Collectors.toList());
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
