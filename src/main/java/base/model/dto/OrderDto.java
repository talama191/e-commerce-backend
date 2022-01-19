package base.model.dto;

import java.sql.Timestamp;

import base.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OrderDto {
	private Long id;
	private Timestamp createdAt;
	private String status;
	private String paymentMode;
	private Long totalPrice;
	private UserDto userDto;
}
