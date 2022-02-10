package base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import base.model.entity.Category;
import base.repository.CategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
	public List<Category> getAll() {
		List<Category> categories =  categoryRepository.findAll();
		for (Category category : categories) {
			System.out.println(category.getProducts());
		}
		return categories;
	}
}
