package base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import base.model.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
    List<Product> findByCategory(String categoryName);

}
