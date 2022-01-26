package base.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import base.model.entity.Cart;
import base.model.entity.form.CartLineForm;
import base.repository.CartRepository;

public class CartService {
	@Autowired
	private CartRepository cartRepo;
	
	public Cart addProductToCart(Cart cart) {
		return cartRepo.save(cart);
	}
	
	public void removeCart(int id) {
		cartRepo.deleteById(id);
	}
	
	
	

}
