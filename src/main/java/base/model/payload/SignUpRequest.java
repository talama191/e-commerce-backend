package base.model.payload;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
	
	private String username;
	
	private String password;
	
	private String fullname;
	
	private String phoneNumber;
	
	private String email;
	
	private String address;
	
	private Character gender;
	
	private Date dob;

}
