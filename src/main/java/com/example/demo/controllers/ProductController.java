package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @RequestMapping(value={"product/index"}, method = RequestMethod.GET)
    public ModelAndView Index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prods", productRepository.findAll());
        modelAndView.setViewName("product/index");
        return modelAndView;
    }

    @RequestMapping(value="/product/create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        Product product= new Product();
        modelAndView.addObject("product", product);
        modelAndView.setViewName("product/create");
        return  modelAndView;
    }

    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public ModelAndView saveRole(@Valid Product product, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("product/create");
            } else {
                    productRepository.save(product);
                modelAndView.setViewName("redirect:/product/index");
            }
            return modelAndView;
        } catch (Exception exp){
            modelAndView.addObject("Error", exp.getMessage());
            modelAndView.addObject("product", product);
            modelAndView.setViewName("product/create");
            return  modelAndView;
        }
    }


    @RequestMapping(value="/product/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, Model model){
        ModelAndView modelAndView = new ModelAndView();
        Product product= productRepository.getOne(id);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("product/edit");
        return  modelAndView;
    }


    @RequestMapping(value="product/edit", method = RequestMethod.POST)
    public ModelAndView editProduct(@ModelAttribute("product") Product product, BindingResult result, ModelMap model)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("product/edit/" + product.getId());
        } else {
            productRepository.save(product);
            modelAndView.setViewName("redirect:/product/index");
        }
        return modelAndView;
    }

    @RequestMapping(value="product/delete/{id}", method = RequestMethod.GET)
    public ModelAndView tipZavedeniaDelete(@PathVariable("id") Long id, Model model){
        Product product= productRepository.findById(id).get();

        productRepository.delete(product);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/product/index");
        return modelAndView;
    }


}
