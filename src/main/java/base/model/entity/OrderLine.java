package base.model.entity;

import lombok.AllArgsConstructor;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import base.model.composite.OrderLineId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_line")
public class OrderLine {
	
	@EmbeddedId
	private OrderLineId id;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "unit_price")
	private Long unitPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(value = "orderId")
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(value = "productId")
	private Product product;
	
}
