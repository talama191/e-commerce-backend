package base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import base.model.composite.CartLineId;
import base.model.entity.Cart;
import base.model.entity.CartLine;
import base.model.entity.Product;

public interface CartlineRepository extends JpaRepository<CartLine, CartLineId>{
    List<CartLine> findByCart(int id);
    @Query("SELECT c FROM CartLine c WHERE c.product = ?1 and c.cart = ?2")
     CartLine findProductInCart(Product product,Cart cart);
}
