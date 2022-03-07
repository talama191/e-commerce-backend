package base.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "voucher")
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "discount_percent")
	private Integer discountPercent;
	
	@Column(name = "expired_at")
	@Nullable
	private Timestamp expiredAt;
	
	@Column(name = "created_at")
	@Nullable
	private Timestamp createdAt;

}
