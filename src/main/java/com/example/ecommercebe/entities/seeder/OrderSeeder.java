package com.example.ecommercebe.entities.seeder;

import com.example.ecommercebe.entities.*;
import com.example.ecommercebe.statics.enums.ProductSimpleStatus;
import com.example.ecommercebe.repositories.*;
import com.example.ecommercebe.util.StringHelper;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class OrderSeeder implements CommandLineRunner {
    OrderRepository orderRepository;
    ProductRepository productRepository;
    OrderDetailRepository orderDetailRepository;
    UserRepository userRepository;
    ShoppingCartRepository shoppingCartRepository;
    CartItemRepository cartItemRepository;
    Faker faker = new Faker();

    public OrderSeeder(OrderRepository orderRepository,
                       ProductRepository productRepository,
                       OrderDetailRepository orderDetailRepository,
                       UserRepository userRepository,
                       ShoppingCartRepository shoppingCartRepository,
                       CartItemRepository cartItemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //check if any value in db, if not, seed data
        if (orderRepository.count() > 0) {
            return;
        }
        createUsers();
        createProducts();
        createOrders();
        //seeding demo cart data
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userRepository.getById(1L));
        Set<CartItem> cartItems = new LinkedHashSet<>();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(productRepository.getById(1));
        cartItem.setQuantity(2);
        cartItem.setTotal(productRepository.findById(1).get().getPrice().multiply(new BigDecimal(2)));
        cartItem.setShoppingCart(shoppingCart);
        cartItems.add(cartItem);
        shoppingCart.setCartItems(cartItems);
        BigDecimal total = new BigDecimal(0);
        for (CartItem c:
             cartItems) {
            total = total.add(c.getTotal());
        }
        shoppingCart.setTotal(total);
        shoppingCartRepository.save(shoppingCart);
        cartItemRepository.save(cartItem);
    }

    private void createOrders() {
        List<Order> orders = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();
        boolean existProduct = false;

        for (int i = 1; i <= 100; i++) {
            Order order = new Order();
            long total = 0;
            int userId = faker.number().numberBetween(1, 10);
            int orderDetailNumber = faker.number().numberBetween(1, 5);

            System.out.println(order.getId());
            order.setStatus(faker.number().numberBetween(1, 5));
            order.setUser(
                    userRepository.findById((long) userId).get());
            for (int j = 0; j < orderDetailNumber; j++) {
                int productId = faker.number().numberBetween(1, 50);
                for (OrderDetail od :
                        orderDetails) {
                    if (od.getProduct().getId() == productId && od.getOrder().getUser().getId() == userId) {
                        existProduct = true;
                        break;
                    }
                }
                if (existProduct) {
                    j--;
                    existProduct = false;
                    continue;
                }
                OrderDetail orderDetail = new OrderDetail();
                Product product = productRepository.findById(productId).get();
                orderDetail.setProduct(product);
                int quantity = faker.number().numberBetween(1, 5);
                orderDetail.setOrder(order);
                orderDetail.setQuantity(quantity);
                long unitPrice = (long) quantity * product.getPrice().intValue();
                orderDetail.setUnitPrice(new BigDecimal(unitPrice));
                total += unitPrice;
                orderDetails.add(orderDetail);
            }
            order.setTotalPrice(new BigDecimal(total));
            orders.add(order);
            if (i % 2 == 0) {
                orderRepository.saveAll(orders);
                orderDetailRepository.saveAll(orderDetails);
                orders.clear();
                orderDetails.clear();
            }
        }
    }

    private void createProducts() {
        boolean nameExisting = false;
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String productName = faker.food().dish();

            for (Product product :
                    products) {
                if (product.getName().equals(productName)) {
                    nameExisting = true;
                    break;
                }
            }
            if (nameExisting) {
                i--;
                nameExisting = false;
                continue;
            }
            String slug = StringHelper.toSlug(productName);
            String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
            String detail = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
            BigDecimal price = new BigDecimal(faker.number().randomNumber(5, true));
            ProductSimpleStatus status = ProductSimpleStatus.ACTIVE;
            Product product = new Product();
            product.setName(productName);
            product.setSlug(slug);
            product.setDescription(description);
            product.setThumbnails("demo-img.jpg");
            product.setPrice(price);
            product.setStatus(status);
            product.setDetail(detail);
            products.add(product);
        }
        productRepository.saveAll(products);
    }

    private void createUsers() {
        boolean nameExisting = false;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StringBuilder username = new StringBuilder();
            String firstName = faker.name().firstName();

            String lastName = faker.name().lastName();
            username.append(firstName.toLowerCase(Locale.ROOT)).append(lastName.toLowerCase(Locale.ROOT));
            for (User user :
                    users) {
                if (user.getUsername().contentEquals(username)) {
                    nameExisting = true;
                    break;
                }
            }
            if (nameExisting) {
                i--;
                nameExisting = false;
                continue;
            }
            String password = "123456";
            String address = faker.address().fullAddress();
            StringBuilder email = new StringBuilder();
            email.append(firstName.toLowerCase(Locale.ROOT)).append(lastName.toLowerCase(Locale.ROOT)).append("@gmail.com");
            String phone = faker.phoneNumber().cellPhone();
            User user = new User(username.toString(), password, address, email.toString(), phone);
            users.add(user);
        }
        userRepository.saveAll(users);
    }
}
