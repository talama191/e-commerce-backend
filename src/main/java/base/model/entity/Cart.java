package base.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@Column(name = "cart_id")
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
	@JsonIgnore
	List<CartLine> cartLines = new ArrayList<>();

}
