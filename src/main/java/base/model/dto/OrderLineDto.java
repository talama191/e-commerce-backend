package base.model.dto;

import base.model.composite.OrderLineId;
import base.model.entity.Order;
import base.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OrderLineDto {
	private OrderLineId id;
	private Integer quantity;
	private Long unitPrice;
	private OrderDto orderDto;
	private ProductDto productDto;

}
