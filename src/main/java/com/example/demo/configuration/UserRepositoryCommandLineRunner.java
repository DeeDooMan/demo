package com.example.demo.configuration;

import com.example.demo.models.Product;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) {
        log.info("Create users");
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");
        roleRepository.save(role);
        User user = new User();
        Set<Role> rl = new HashSet<Role>();
        rl.add(role);
        user.setUserName("u");
        user.setRoles(rl);
        user.setActive(1);
        user.setPassword(bCryptPasswordEncoder.encode("u"));
        userRepository.save(user);

        Product product =new Product();
        product.setName("HardDisk");
        product.setPrice(120f);
        productRepository.save(product);
        product =new Product();
        product.setName("RAM");
        product.setPrice(80f);
        productRepository.save(product);

    }
}