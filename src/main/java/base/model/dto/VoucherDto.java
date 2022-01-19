package base.model.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VoucherDto {
	private Integer id;
	private String code;
	private Integer discountPercent;
	private Timestamp expiredAt;
	private Timestamp createdAt;
}
