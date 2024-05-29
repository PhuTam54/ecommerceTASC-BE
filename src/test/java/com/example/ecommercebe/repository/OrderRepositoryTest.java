package com.example.ecommercebe.repository;

import com.example.ecommercebe.EcommerceBeApplication;
import com.example.ecommercebe.config.H2JpaConfig;
import com.example.ecommercebe.entities.Order;
import com.example.ecommercebe.entities.OrderDetail;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.entities.User;
import com.example.ecommercebe.repositories.OrderDetailRepository;
import com.example.ecommercebe.repositories.OrderRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.repositories.UserRepository;
import com.example.ecommercebe.statics.enums.ProductSimpleStatus;
import com.example.ecommercebe.util.StringHelper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EcommerceBeApplication.class, H2JpaConfig.class})
@ActiveProfiles("test")
class OrderRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;
    Faker faker = new Faker();

    @BeforeEach
    public void before() {
        createProducts();
        saveUsers();
        System.out.println("hello");
    }
    @Test
    void createOrders() {
        List<Order> orders = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();
        boolean existProduct = false;

        for (int i = 1; i <= 10; i++) {
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
                Product product = productRepository.findById((long) productId).get();
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
        }
        orderRepository.saveAll(orders);
        orderDetailRepository.saveAll(orderDetails);
        orderRepository.findAll().forEach(ord -> {
            System.out.println(ord.toString());
        });
    }

    void saveUsers() {
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
            StringBuilder accountName = new StringBuilder();
            accountName.append(firstName).append(" ").append(lastName);
            String address = faker.address().fullAddress();
            StringBuilder email = new StringBuilder();
            email.append(firstName.toLowerCase(Locale.ROOT)).append(lastName.toLowerCase(Locale.ROOT)).append("@gmail.com");
            String phone = faker.phoneNumber().cellPhone();
            User user = new User(username.toString(), password, address, email.toString(), phone);
            users.add(user);
        }
        userRepository.saveAll(users);
        userRepository.findAll().forEach(user -> {
            System.out.println(user.toString());
        });
    }

    void createProducts() {
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
//            product.setSlug(slug);
            product.setDescription(description);
//            product.setThumbnails(slug + "-image1.png");
            product.setPrice(price);
//            product.setStatus(status);
            product.setDetail(detail);
            products.add(product);
        }
        productRepository.saveAll(products);
        productRepository.findAll().forEach(product -> {
            System.out.println(product.toString());
        });
    }
}