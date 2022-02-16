package base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import base.model.dto.CartLineDto;
import base.model.entity.Cart;
import base.model.entity.CartLine;
import base.model.entity.Product;
import base.model.entity.form.CartLineForm;
import base.service.CartService;

@RestController
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value = "/add/{cartId}", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CartLine> addToCart(@PathVariable int cartId, @RequestBody CartLineForm cartLineForm){
		
		return ResponseEntity.ok().body(cartService.addProductToCartLine(cartId, cartLineForm));
	}
	
	@RequestMapping(value = "viewCart", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CartLine>> viewCart(int id){
		
		return ResponseEntity.ok().body(cartService.viewCartLine(id));
	}

}
