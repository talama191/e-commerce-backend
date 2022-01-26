package base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import base.model.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
