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
		
		CartLine cartLine = new CartLine();
		
		CartLineId cartLineId = new CartLineId(cartId, (int)cartLineForm.getProductId());
		cartLine.setId(cartLineId);
		cartLine.setCart(cart);
		cartLine.setQuantity(cartLineForm.getQuantity());
		cartLine.setProduct(product);
		
		
		
		
		return cartlineRepository.save(cartLine);
	}
	
	public void removeCart(int id) {
		cartRepo.deleteById(id);
	}
	
	public List<CartLine> viewCartLine(){
		List<CartLine> cartLines = cartlineRepository.findAll();
		return cartLines;
	}
	
	
	

}
