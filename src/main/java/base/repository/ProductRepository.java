package base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import base.model.entity.Category;
import base.model.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	public List<Product> findByCategory(String categoryName);
	public Product findById(int id);
	public Product findByName(String name);
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
            + " OR p.id LIKE %?1%"
            + " OR p.category.name LIKE %?1%")
    public List<Product> search(String keyword);	
    

}
