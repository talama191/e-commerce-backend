package base.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import base.model.dto.ProductDto;
import base.model.entity.Category;
import base.model.entity.Product;
import base.repository.CategoryRepository;
import base.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
    private ProductService service;
	@Autowired
	private CategoryRepository cateRepo;
    // view List
    @GetMapping("viewList")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public  ResponseEntity<List<ProductDto>> listProducts(){	
    	return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping("{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Product> getById(@PathVariable int id){
    	return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }
    @PostMapping("add")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Product> addProduct(@RequestBody Product p){
       return ResponseEntity.status(HttpStatus.OK).body(service.save(p));
    }
    @GetMapping("getByCategory/{categoryName}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String categoryName){
    	Category c = cateRepo.findByName(categoryName);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(c.getProducts());
    }
    // pagination
    @GetMapping("pagination")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public Page<ProductDto> getPage(@RequestParam Optional<Integer> page , 
    		                     @RequestParam Optional<String> sortBy ,
    		                     @RequestParam Optional<String> direction){

    	Sort sort = direction.orElse("asc").equalsIgnoreCase(Sort.Direction.ASC.name()) 
    			? Sort.by(sortBy.orElse("id")).ascending() : Sort.by(sortBy.orElse("id")).descending();
    	
        Pageable pageable = PageRequest.of(
        		page.orElse(0), // the current page
        		100, // the size of page
        		sort// sort by or else id
        		);
    	return service.findAllPage(pageable);
    }
    
    
    @GetMapping("search")
    public ResponseEntity<List<Product>> search(String keyword){
    	return ResponseEntity.ok().body(service.search(keyword));
    }
    
    @GetMapping("trending")
    public ResponseEntity<List<Product>> getTrendindProduct(){
    	return ResponseEntity.ok().body(service.topTrending());
    }
}
