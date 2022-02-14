package base.model.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import base.model.composite.CartLineId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_line")
public class CartLine {
	@EmbeddedId
	private CartLineId id;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "unit_price")
	private Long unitPrice;
	
	@ManyToOne()
	@MapsId(value = "cartId")
	private Cart cart;
	
	@ManyToOne()
	@MapsId(value = "productId")
	private Product product;
}
