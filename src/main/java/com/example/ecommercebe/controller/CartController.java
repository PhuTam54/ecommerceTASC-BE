//package com.example.ecommercebe.controller;
//
//import com.example.ecommercebe.entities.CartItem;
//import com.example.ecommercebe.entities.CartItemId;
//import com.example.ecommercebe.entities.ShoppingCart;
//import com.example.ecommercebe.models.requests.CartItemRequest;
//import com.example.ecommercebe.service.CartItemService;
////import com.example.ecommercebe.service.CartService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@Tag(name = "Cart", description = "Cart Controller")
//@CrossOrigin()
//@RestController
//@RequestMapping("api/v1/carts")
//public class CartController {
//    private final CartService cartService;
//    private final CartItemService cartItemService;
//
//    public CartController(CartService cartService, CartItemService cartItemService) {
//        this.cartService = cartService;
//        this.cartItemService = cartItemService;
//    }
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<?> getUserCart(@RequestParam(name = "userId") Long userId) {
//        ShoppingCart shoppingCart = cartService.findShoppingCartByUserId(userId);
//        if (shoppingCart != null){
//            return ResponseEntity.ok(shoppingCart);
//        }
//        return ResponseEntity.notFound().build();
//    }
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<?> addItemToCart(@RequestBody CartItemRequest cartItemRequest){
//        return ResponseEntity.ok(cartService.saveCartItem(cartItemRequest));
//    }
//    @RequestMapping(method = RequestMethod.POST, path = "delete")
//    public ResponseEntity<?> removeCartItem(@RequestBody CartItemId cartItemId){
//        CartItem returnResult = cartService.removeCartItem(cartItemId);
//        if (returnResult != null) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @RequestMapping(method = RequestMethod.GET, path = "cartItems")
//    public ResponseEntity<?> getCartItems(@RequestParam(name = "cartId") int cartId){
//        return ResponseEntity.ok(cartItemService.findCartItemsByShoppingCartId(cartId));
//    }
//}
