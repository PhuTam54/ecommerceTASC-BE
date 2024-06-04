package com.example.ecommercebe.repository;

import com.example.ecommercebe.EcommerceBeApplication;
import com.example.ecommercebe.config.H2JpaConfig;
import com.example.ecommercebe.entities.User;
import com.example.ecommercebe.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EcommerceBeApplication.class, H2JpaConfig.class})
@ActiveProfiles("test")
class UserRepositoryTest {
    private final Faker faker = new Faker();
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUsers(){
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
}