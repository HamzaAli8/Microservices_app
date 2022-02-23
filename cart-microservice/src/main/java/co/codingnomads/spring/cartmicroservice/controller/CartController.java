package co.codingnomads.spring.cartmicroservice.controller;


import co.codingnomads.spring.cartmicroservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(cartService.getCartByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add/{userId}/{itemId}")
    public ResponseEntity<?> addNewCartItem(@PathVariable Long itemId, @PathVariable Long userId) {
        try {
            return ResponseEntity.ok(cartService.addCartItem(itemId, userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/remove/{userId}/{cartItemId}")
    public ResponseEntity<?> removeCartItem(@PathVariable("cartItemId") Long cartItemId,
                                            @PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(cartService.removeCartItem(cartItemId, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{userId}/{cartItemId}/{amount}")
    public ResponseEntity<?> updateItemAmount(@PathVariable("userId") Long userId,
                                              @PathVariable("cartItemId") Long cartItemId,
                                              @PathVariable("amount") Integer amount) {
        try {
            return ResponseEntity.ok(cartService.updateAmount(userId, cartItemId, amount));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
