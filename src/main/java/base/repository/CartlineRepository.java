package base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import base.model.composite.CartLineId;
import base.model.entity.Cart;
import base.model.entity.CartLine;

public interface CartlineRepository extends JpaRepository<CartLine, CartLineId>{
    List<CartLine> findByCart(int id);

}
