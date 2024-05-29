package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.*;
import com.example.ecommercebe.models.requests.CartItemRequest;
import com.example.ecommercebe.repositories.CartItemRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.repositories.ShoppingCartRepository;
import com.example.ecommercebe.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class CartServiceTest {
    private static int USER_ID = 1;
    private static int PRODUCT_ID = 1;
    private static int SHOPPING_CART_ID = 1;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartService cartService;
    @BeforeEach
    public void setUp() {
        log.info("Set up shoppingCart start");
        Product product = new Product();
        product.setPrice(new BigDecimal(300000));
        product.setDetail("Detail");
        product.setName("Product 1");
        PRODUCT_ID = Math.toIntExact(productRepository.save(product).getId());
        User user = new User();
        user.setUsername("Test User");
        USER_ID = (int) userRepository.save(user).getId();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        Set<CartItem> cartItems = new HashSet<>();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setQuantity(3);
        cartItem.setTotal(new BigDecimal(3).multiply(product.getPrice()));
        cartItems.add(cartItem);
        shoppingCart.setCartItems(cartItems);
        BigDecimal total = new BigDecimal(0);
        for (CartItem cartItem1:
                cartItems) {
            total = total.add(cartItem1.getTotal());
        }
        shoppingCart.setTotal(total);
        SHOPPING_CART_ID = shoppingCartRepository.save(shoppingCart).getId();
        cartItemRepository.save(cartItem);
        log.info("Set up shoppingCart end");
    }
    @Test
    void givenUserId_whenFindShoppingCartByUserId_thenReturnShoppingCartOfGivenUserId() {

        ShoppingCart shoppingCart = cartService.findShoppingCartByUserId((long) USER_ID);

        assertEquals(USER_ID, shoppingCart.getUser().getId());
        assertEquals(1, shoppingCart.getCartItems().size());
        assertEquals(PRODUCT_ID, shoppingCart.getCartItems().stream().findFirst().map(CartItem::getProduct).map(Product::getId).orElse((long) -1));
    }

    @Test
    void givenCartItemId_whenRemoveCartItemIsLastItem_thenRemoveCartItemAndShoppingCartReturnRemovedCartItem() {
        CartItemId cartItemId = new CartItemId();
        cartItemId.setProductId(PRODUCT_ID);
        cartItemId.setShoppingCartId(SHOPPING_CART_ID);

        CartItem removedCartItem = cartService.removeCartItem(cartItemId);

        assertNotNull(removedCartItem);
        assertEquals(cartItemId, removedCartItem.getCartItemId());
        assertFalse(shoppingCartRepository.findById(SHOPPING_CART_ID).isPresent());
    }

    @Test
    void givenCartItemId_whenRemoveCartItem_thenRemoveCartItemReturnRemovedCartItem() {
        Product product = new Product();
        product.setId((long) ++PRODUCT_ID);
        product.setPrice(new BigDecimal(300000));
        product.setDetail("Detail");
        product.setName("Product 2");
        productRepository.save(product);
        cartService.saveCartItem(new CartItemRequest((long) PRODUCT_ID, (long) USER_ID, 3));
        CartItemId cartItemId = new CartItemId();
        cartItemId.setProductId(PRODUCT_ID);
        cartItemId.setShoppingCartId(SHOPPING_CART_ID);

        CartItem removedCartItem = cartService.removeCartItem(cartItemId);

        assertNotNull(removedCartItem);
        assertEquals(cartItemId, removedCartItem.getCartItemId());
        assertTrue(shoppingCartRepository.findById(SHOPPING_CART_ID).isPresent());

    }

    @Test
    void givenCartItemId_whenRemoveCartItemNotFoundCartItem_thenReturnNull() {

        CartItemId cartItemId = new CartItemId();
        cartItemId.setProductId(-1);
        cartItemId.setShoppingCartId(-1);

        CartItem removedCartItem = cartService.removeCartItem(cartItemId);

        assertNull(removedCartItem);
    }
    @AfterEach
    public void tearDown() {
        cartItemRepository.deleteAll();
        shoppingCartRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

}