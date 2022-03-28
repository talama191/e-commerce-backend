package base.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.Nullable;

import base.model.dto.ProductDto;
import base.model.entity.Product;
import base.model.entity.form.ProductInsertForm;
import base.repository.CategoryRepository;
import base.repository.ProductRepository;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Service
@EnableTransactionManagement
public class ProductService {
	@Autowired
	private ProductRepository repo;
	@Autowired
	private MinioService minioService;
	@Autowired
	private CategoryRepository categoryRepository;
    
	// list products DTO

	public List<ProductDto> findAll() {
		List<Product> products = repo.findAll();
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		
		for(Product p : products) {
			ModelMapper mapper = new ModelMapper();
			ProductDto prodDto = mapper.map(p, ProductDto.class);
			productDtos.add(prodDto);
		}
		
		return productDtos;
	}
	

	public ProductDto mapToDto(Product product) {
		ModelMapper mapper = new ModelMapper();
		ProductDto proDto = mapper.map(product, ProductDto.class);
		
		return proDto;
	}
	
	public Product save(String name,
			            Long price,
			            String shortDescription,
			            String longDescription,
			            String categoryName,
			            MultipartFile img1,
			            @Nullable MultipartFile img2) throws InvalidKeyException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidResponseException, XmlParserException, InternalException, IOException {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setShortDescription(shortDescription);
		product.setLongDescription(longDescription);
		product.setCategory(categoryRepository.findByName(categoryName));
		product.setImg1(minioService.upload(img1, "bucket"));
		product.setImg2(minioService.upload(img2, "bucket"));
		return repo.save(product);
	}
	
	public Product findByName(String name) {
		return repo.findByName(name);
	}

	
	public Product getById(int id) {
		return repo.findById(id);
	}

	public Page<ProductDto> findAllPage(Pageable pageable) {
		Page<Product> entities = repo.findAll(pageable);
		Page<ProductDto> dtoPage = entities.map(new Function<Product, ProductDto>() {

			@Override
			public ProductDto apply(Product t) {
				ProductDto dto = mapToDto(t);
				
				return dto;
			}
			
		});
		return dtoPage;
	}
	
	public List<Product> search(String keyword){ 
		if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
	}
	
	
	public List<Product> topTrending(){
		List<Product> products = repo.findAll();
		List<Product> trending = new ArrayList<Product>();
		trending.add(products.get(0));
		trending.add(products.get(7));
		trending.add(products.get(2));
		trending.add(products.get(21));
		trending.add(products.get(10));
		trending.add(products.get(16));
		return trending;
	}
	
	
	



}
