package base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.model.composite.CartLineId;
import base.model.dto.CartLineDto;
import base.model.dto.ProductDto;
import base.model.entity.Cart;
import base.model.entity.CartLine;
import base.model.entity.Product;
import base.model.entity.form.CartLineForm;
import base.repository.CartRepository;
import base.repository.CartlineRepository;
import base.repository.ProductRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private CartlineRepository cartlineRepository;
	@Autowired
	private ProductRepository productRepository;
	
	public CartLine addProductToCartLine( int cartId,CartLineForm cartLineForm) {
		Cart cart = cartRepo.findById(cartId);
		Product product = productRepository.findById((int)cartLineForm.getProductId());
		
		if(ifExisted(product, cartId)) {
			CartLine cartLine = cartlineRepository.findProductInCart(product, cart);
			int quantity = cartLine.getQuantity() +cartLineForm.getQuantity();
			cartLine.setQuantity(quantity);
			return cartlineRepository.save(cartLine);
		}else {
			
			CartLine cartLine = new CartLine();
			
			CartLineId cartLineId = new CartLineId(cartId, (int)cartLineForm.getProductId());
			cartLine.setId(cartLineId);
			cartLine.setCart(cart);
			cartLine.setQuantity(cartLineForm.getQuantity());
			cartLine.setProduct(product);
			return cartlineRepository.save(cartLine);
		}
	  
		
	}

	public int clearCart(int cartId){
		cartlineRepository.clearCart(cartId);
		return 1;
	}
	
	public void removeItem(int cartId , int productId) {
		Cart cart = cartRepo.findById(cartId);
		Product p = productRepository.findById(productId);
		CartLine cartLine = cartlineRepository.findProductInCart(p, cart);
		cartlineRepository.delete(cartLine);
	}
	
	public CartLine plusItem(int productId ,int cartId) {
		Cart cart = cartRepo.findById(cartId);
		Product p = productRepository.findById(productId);
		CartLine cartLine = cartlineRepository.findProductInCart(p, cart);
		int quantity = cartLine.getQuantity() +1;
		cartLine.setQuantity(quantity);
		return cartlineRepository.save(cartLine);
	}
	
	
	public void minus(int productId ,int cartId) {
		Cart cart = cartRepo.findById(cartId);
		Product p = productRepository.findById(productId);
		if(p!=null) {
			CartLine cartLine = cartlineRepository.findProductInCart(p, cart);
			int quantity = cartLine.getQuantity() - 1;
			if(quantity ==0) {
				removeItem(cartId, productId);
			}else {
				cartLine.setQuantity(quantity);
				cartlineRepository.save(cartLine);
			
		}
}	
	}
	

	
	public boolean ifExisted(Product p , int cartId) {
		Cart cart = cartRepo.findById(cartId);
		List<CartLine> carts = cart.getCartLines();
		for(CartLine cartLine:carts) {
			if(cartLine.getProduct().equals(p)) {
                return true;
			}
		}
		
		return false;
	}
	
	public List<CartLine> viewCartLine(int id){
	    Cart cart = cartRepo.findById(id);
		List<CartLine> carts = cart.getCartLines();
		return carts;
	}
	
	public int geTotalPrice(int cartId) {
		  Cart cart = cartRepo.findById(cartId);
		  int totalAll = 0;
		  List<CartLine> carts = cart.getCartLines();
		  for(CartLine c:carts) {
			  int total = (int) (c.getQuantity() * c.getProduct().getPrice());
			  totalAll+=total;
		  }
		  return totalAll;
		  
	}
	
	public void saveCart(Cart c) {
		cartRepo.save(c);
	}
	
	
	

}
