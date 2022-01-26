package base.model.entity.form;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CartLineForm {

	@NotNull
	@Min(value = 1)
	private Integer productId;
	
	@NotNull
	@Min(value = 1)
	private Integer quantity;
}
