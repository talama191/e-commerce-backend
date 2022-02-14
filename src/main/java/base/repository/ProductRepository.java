package base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import base.model.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
    List<Product> findByCategory(String categoryName);
    Product findById(int id);
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
            + " OR p.id LIKE %?1%"
            + " OR p.category.name LIKE %?1%")
    public List<Product> search(String keyword);

}
