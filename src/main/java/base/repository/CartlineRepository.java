package base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import base.model.composite.CartLineId;
import base.model.entity.CartLine;

public interface CartlineRepository extends JpaRepository<CartLine, CartLineId>{

}
