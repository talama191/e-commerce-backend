package base.model.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private Integer id;
	private String username;
	private String password;
	private String fullname;
	private String phoneNumber;
	private String email;
	private String address;
	private Character gender;
	private Date dob;
	private CartDto cart;
	private Set<RoleDto> role;
	List<OrderDto> ordersDto = new ArrayList<>();
}
