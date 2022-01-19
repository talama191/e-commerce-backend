package base.service;

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

import base.model.dto.ProductDto;
import base.model.entity.Product;
import base.repository.ProductRepository;

@Service
@EnableTransactionManagement
public class ProductService {
	@Autowired
	private ProductRepository repo;
    
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
	
	public Product save(Product p) {
		return repo.save(p);
	}
	
	public Optional<Product> getById(int id) {
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
	


}
