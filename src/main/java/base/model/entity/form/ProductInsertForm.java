package base.model.entity.form;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInsertForm {
	
	private String name;
	private long price;
	private String shortDescription;
	private String longDescription;
	private String CategoryName;
	private MultipartFile img1;
	@Nullable
	private MultipartFile img2;
	

}
