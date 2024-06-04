package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.UserDTO;
import com.example.ecommercebe.entities.Role;
import com.example.ecommercebe.entities.User;
import com.example.ecommercebe.mapper.UserMapper;
import com.example.ecommercebe.models.requests.UserRequest;
import com.example.ecommercebe.repositories.RoleRepository;
import com.example.ecommercebe.repositories.UserRepository;
import com.example.ecommercebe.statics.enums.ERole;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public Page<UserDTO> getAll(Pageable pageable) {
        Page<User> userPage = userRepository.findByDeletedAtIsNull(pageable);
        return userPage.map(UserMapper.INSTANCE::userToUserDTO);
    }

    public Page<UserDTO> getInTrash(Pageable pageable) {
        Page<User> userPage = userRepository.findByDeletedAtIsNotNull(pageable);
        return userPage.map(UserMapper.INSTANCE::userToUserDTO);
    }
  
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Cannot find this user id: " + id);
        }
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Cannot find this username: " + username);
        }
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    public void moveToTrash(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Cannot find this user id: " + id);
        }
        LocalDateTime now = LocalDateTime.now();
        user.setDeletedAt(now);

        userRepository.save(user);
    }

    public UserDTO createUser(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);

        Set<String> strRoles = userRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        String password = encoder.encode(userRequest.getPassword());
        user.setRoles(roles);
        user.setPassword(password);

        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(savedUser);
    }

    public UserDTO updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }

        String oldPassword = user.getPassword();

        BeanUtils.copyProperties(userRequest, user);

        Set<String> strRoles = userRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        if (userRequest.getPassword() != null && userRequest.getPassword().length() >= 6){
            user.setPassword(encoder.encode(userRequest.getPassword()));
        } else {
            user.setPassword(oldPassword);
        }

        if (userRequest.getRoles() != null && userRequest.getRoles().size() > 0 ){
            user.setRoles(roles);
        }

        if (userRequest.getAvatar() != null && !Objects.equals(user.getAvatar(),
            userRequest.getAvatar().substring(userRequest.getAvatar().lastIndexOf('/') + 1))){
            user.setAvatar(userRequest.getAvatar());
        }

        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(savedUser);
    }
  
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
