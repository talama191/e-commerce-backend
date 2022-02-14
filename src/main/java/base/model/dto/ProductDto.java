package base.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import base.model.entity.CartLine;
import base.model.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
	private Integer id;
	private String name;
	private Long price;
	private String img1;
	private String img2;
	private String shortDescription;
	private String longDescription;
	private CategoryDto category;
	List<CartLineDto> cartLinesDto = new ArrayList<>();

}
