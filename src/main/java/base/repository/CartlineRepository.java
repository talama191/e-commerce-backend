package base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import base.model.composite.CartLineId;
import base.model.entity.Cart;
import base.model.entity.CartLine;
import base.model.entity.Product;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CartlineRepository extends JpaRepository<CartLine, CartLineId> {
    List<CartLine> findByCart(int id);

    @Query("SELECT c FROM CartLine c WHERE c.product = ?1 and c.cart = ?2")
    CartLine findProductInCart(Product product, Cart cart);

    @Transactional
    @Modifying
    @Query(value = "Delete from Cart_Line where cart_cart_id= :id ", nativeQuery = true)
    void clearCart(@Param("id") int cartId);
}
