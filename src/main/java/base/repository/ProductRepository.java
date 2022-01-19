package base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import base.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
