package com.example.ecommercebe.controller;

import com.example.ecommercebe.entities.CartItem;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.entities.ShoppingCart;
import com.example.ecommercebe.entities.User;
import com.example.ecommercebe.repositories.CartItemRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.repositories.ShoppingCartRepository;
import com.example.ecommercebe.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class CartControllerTest extends AbstractApiTest {
    private static int USER_ID = 1;
    private static int PRODUCT_ID = 1;
    private static int SHOPPING_CART_ID = 1;
    private static final String URI = "/api/v1/carts";

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @BeforeEach
    public void setUp() {
        super.setUp();
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
    public void givenUserId_whenGetUserCart_thenReturnResponseEntityOkWithShoppingCart() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(URI)
                .param("userId", String.valueOf(USER_ID))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());
        log.info("response body: {}", response.getContentAsString());
        ShoppingCart shoppingCart = super.mapFromJson(response.getContentAsString(), ShoppingCart.class);
        Assertions.assertNotNull(shoppingCart);
        Assertions.assertEquals(SHOPPING_CART_ID, shoppingCart.getId());
    }

    @Test
    public void givenUserId_whenGetUserCartNotFound_thenReturnResponseEntityNotFound() throws Exception {
        USER_ID = -1;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(URI)
                .param("userId", String.valueOf(USER_ID))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(404, response.getStatus());
        log.info("response body: {}", response.getContentAsString());
    }


    @AfterEach
    public void tearDown() {
        cartItemRepository.deleteAll();
        shoppingCartRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }
}
