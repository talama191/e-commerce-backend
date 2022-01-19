package base.model.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderLineId implements Serializable {

	private static final long serialVersionUID = -3582052521898928329L;
	
	@Column(name = "order_id", nullable = false, insertable = false, updatable = false)
	private Long orderId;
	
	@Column(name = "product_id", nullable = false, insertable = false, updatable = false)
	private Integer productId;
	
	@Override
	public int hashCode() {
		final int prime = 33; 
		int hash = 19;
		
		hash = hash * prime + orderId.hashCode();
		hash = hash * prime + productId.hashCode();
		
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof OrderLineId))
			return false;
		OrderLineId castedObj = (OrderLineId) obj;
		return this.orderId.equals(castedObj.orderId) && this.productId.equals(castedObj.productId);
	}
}
