package com.example.demo.controllers;

import com.example.demo.models.Orders;
import com.example.demo.models.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MyController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;
    @RequestMapping(path = "add")
    public @ResponseBody String AddNewPRoduct(@RequestParam String name, @RequestParam Float price){
//        Product p=new Product();
//        p.setPrice(price);
//        p.setName(name);
//        productRepository.save(p);

        return  "Saved";
    }

    @RequestMapping(path = "/all")
    public @ResponseBody Iterable<Product> getAll(){
//
//        Product p=new Product("Phone", 1545f);
//
//        Orders ord=new Orders(10);
//        Orders ord2=new Orders(15);
//        p.addOrders(ord);
//        p.addOrders(ord2);
//
//        productRepository.save(p);
//        orderRepository.save(ord);
//        orderRepository.save(ord2);

       return   productRepository.findAll();
    }
}
