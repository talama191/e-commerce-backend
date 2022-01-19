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
public class CartLineId implements Serializable {

	private static final long serialVersionUID = 3128147993246840843L;
	
	@Column(name = "cart_id", nullable = false, insertable = false, updatable = false)
	private Integer cartId;
	
	@Column(name = "product_id", nullable = false, insertable = false, updatable = false)
	private Integer productId;
	
	@Override
	public int hashCode() {
		final int prime = 31; 
		int hash = 17;
		
		hash = hash * prime + cartId.hashCode();
		hash = hash * prime + productId.hashCode();
		
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof CartLineId))
			return false;
		CartLineId castedObj = (CartLineId) obj;
		return this.cartId.equals(castedObj.cartId) && this.productId.equals(castedObj.productId);
	}
}
