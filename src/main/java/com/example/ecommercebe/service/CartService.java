package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.*;
import com.example.ecommercebe.models.requests.CartItemRequest;
import com.example.ecommercebe.repositories.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService {
    private CartItemRepository cartItemRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private ProductRepository productRepository;
    private ClinicRepository clinicRepository;
    private UserRepository userRepository;

    public CartService(CartItemRepository cartItemRepository, ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, ClinicRepository clinicRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.clinicRepository = clinicRepository;
    }

    public ShoppingCart findShoppingCartByUserId(Integer userId) {
        return shoppingCartRepository.findShoppingCartByUser_Id(userId.longValue());
    }

    public ShoppingCart saveCartItem(CartItemRequest cartItem) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser_Id(cartItem.getUserId());
        // if there's an existing shopping cart of a user
        if (shoppingCart != null) {
            CartItem itemExisting = null;
            for (CartItem item :
                    shoppingCart.getCartItems()) {
                if (item.getProduct().getId() == cartItem.getProductId()) {
                    itemExisting = item;
                    break;
                }
            }
            int quantity;
            Optional<Product> product = productRepository.findById(cartItem.getProductId());
            Optional<Clinic> clinic = clinicRepository.findById(cartItem.getClinicId());
            if (itemExisting != null) {
                quantity = itemExisting.getQuantity() + cartItem.getQuantity();
            } else {
                quantity = cartItem.getQuantity();
                itemExisting = new CartItem();
                itemExisting.setShoppingCart(shoppingCart);
                itemExisting.setTotal(new BigDecimal(0));
                itemExisting.setProduct(product.get());
                itemExisting.setClinic(clinic.get());
            }
            if (product.isPresent() && clinic.isPresent()) {
                BigDecimal oldCartItemTotal = itemExisting.getTotal();
                itemExisting.setQuantity(quantity);
                itemExisting.setTotal(BigDecimal.valueOf(product.get().getPrice()).multiply(new BigDecimal(quantity)));
                shoppingCart.setTotal(shoppingCart.getTotal().subtract(oldCartItemTotal).add(itemExisting.getTotal()));
                shoppingCartRepository.save(shoppingCart);
                cartItemRepository.save(itemExisting);
            }
        } else {
            ShoppingCart newShoppingCart = new ShoppingCart();
            Set<CartItem> cartItems = new LinkedHashSet<>();
            newShoppingCart.setUser(userRepository.findById(cartItem.getUserId()).get());
            CartItem newCartItem = createNewCartItem(cartItem, newShoppingCart);
            newShoppingCart.setTotal(newCartItem.getTotal());
            cartItems.add(newCartItem);
            newShoppingCart.setCartItems(cartItems);
            shoppingCartRepository.save(newShoppingCart);
            cartItemRepository.save(newCartItem);
        }
        return shoppingCart;
    }
    public CartItem removeCartItem(CartItemId cartItemId) {
        Optional<CartItem> removedCartItem = cartItemRepository.findById(cartItemId);
        if (removedCartItem.isPresent()){
            ShoppingCart shoppingCart = removedCartItem.get().getShoppingCart();
            shoppingCart.setTotal(shoppingCart.getTotal().subtract(removedCartItem.get().getTotal()));
            if (shoppingCart.getTotal().equals(new BigDecimal(0))){
                shoppingCartRepository.delete(shoppingCart);
            }else {
                shoppingCartRepository.save(shoppingCart);
            }
            cartItemRepository.delete(removedCartItem.get());
            return removedCartItem.get();
        }
        return null;
    }
    private CartItem createNewCartItem(CartItemRequest cartItem, ShoppingCart shoppingCart){
        Optional<Product> product = productRepository.findById(cartItem.getProductId());
        Optional<Clinic> clinic = clinicRepository.findById(cartItem.getClinicId());
        CartItem newCartItem = new CartItem();
        int quantity = cartItem.getQuantity();
        if (product.isPresent() && clinic.isPresent()) {
            newCartItem.setProduct(product.get());
            newCartItem.setQuantity(quantity);
            newCartItem.setTotal(BigDecimal.valueOf(product.get().getPrice()).multiply(new BigDecimal(quantity)));
            newCartItem.setShoppingCart(shoppingCart);
            newCartItem.setClinic(clinic.get());
        }
        return newCartItem;
    }
}
