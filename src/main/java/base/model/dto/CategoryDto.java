package base.model.dto;

import java.util.ArrayList;
import java.util.List;

import base.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	private Integer id;
	private String name;
	List<ProductDto> productsDto = new ArrayList<>(); 
}
