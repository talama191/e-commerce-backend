package base.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.Nullable;

import base.model.dto.ProductDto;
import base.model.entity.Category;
import base.model.entity.Product;
import base.model.entity.form.ProductInsertForm;
import base.repository.CategoryRepository;
import base.service.ProductService;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
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
    @PostMapping(value="add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Product> addProduct(
    	  @RequestParam	String name,
    	  @RequestParam Long price,
    	  @RequestParam String shortDescription,
    	  @RequestParam String longDescription,
    	  @RequestParam String categoryName,
    	  @RequestPart(value = "img1") MultipartFile img1,
    	  @RequestPart(value = "img2")  @Nullable MultipartFile img2) throws InvalidKeyException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidResponseException, XmlParserException, InternalException, IOException  {
   
    	return ResponseEntity.status(HttpStatus.OK).body(service.save(name, price, shortDescription, longDescription, categoryName, img1, img2));
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
    
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteProduct(int id){
    	service.deleteProduct(id);
    	return ResponseEntity.ok().body("product deleted");
    }
    
}
