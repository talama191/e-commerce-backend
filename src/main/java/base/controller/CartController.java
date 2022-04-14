package base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/add/{cartId}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CartLine> addToCart(@PathVariable int cartId, @RequestBody CartLineForm cartLineForm) {
        return ResponseEntity.ok().body(cartService.addProductToCartLine(cartId, cartLineForm));
    }

    @RequestMapping(value = "viewCart", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CartLine>> viewCart(int id) {

        return ResponseEntity.ok().body(cartService.viewCartLine(id));
    }

    @GetMapping("getTotal")
    public ResponseEntity<Integer> geTotalPrice(int cartId) {
        return ResponseEntity.ok().body(cartService.geTotalPrice(cartId));
    }

    @DeleteMapping(value = "/clear/{cartId}")
    public ResponseEntity<Integer> clearCart(@PathVariable int cartId) {
        return ResponseEntity.ok().body(cartService.clearCart(cartId));
    }

    @PutMapping("plus")
    public ResponseEntity<CartLine> plusCart(int productId, int cartId) {
        return ResponseEntity.ok().body(cartService.plusItem(productId, cartId));
    }

    @PutMapping("minus")
    public Map<String, Boolean> minusCart(int productId, int cartId) {
        cartService.minus(productId, cartId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("minus", Boolean.TRUE);
        return response;
    }


}
