package base.model.dto;

import base.model.composite.CartLineId;
import base.model.entity.Cart;
import base.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CartLineDto {
	private CartLineId id;
	private Integer quantity;
	private Long unitPrice;
	private Cart cart;
	private ProductDto productDto;
}
