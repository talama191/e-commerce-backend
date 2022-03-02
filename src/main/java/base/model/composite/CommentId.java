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
public class CommentId implements Serializable{

	private static final long serialVersionUID = 3583052521898928329L;
	@Column(name = "product_id", nullable = false, insertable = false, updatable = false)
	private Integer productId;
	
	@Column(name = "user_id", nullable = false, insertable = false, updatable = false)
	private Integer userId;
	

	
	@Override
	public int hashCode() {
		final int prime = 31; 
		int hash = 17;
		
		hash = hash * prime + userId.hashCode();
		hash = hash * prime + productId.hashCode();
		
		return hash;
	}
}
