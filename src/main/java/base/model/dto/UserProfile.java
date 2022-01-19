package base.model.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserProfile {
	private Integer id;
	private String username;
	private String password;
	private String fullname;
	private String phoneNumber;
	private String email;
	private String address;
	private Character gender;
	private Date dob;
	private Boolean enabled;

}
