package base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import base.model.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	public Cart findById(int id);

}
