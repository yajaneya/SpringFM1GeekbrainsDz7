package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;


import org.springframework.web.bind.annotation.RequestParam;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public int get () {
        return 1;
    }

    @GetMapping("/product/{id}")
    public Product getProductById (@PathVariable Long id) {
        return productService.findByID(id).get();
    }

    @GetMapping("/products")
    public List<Product> getProducts () {
        return productService.findAll();
    }

    @GetMapping("/products/between/page")
    public List<Product> getBetweenPage (@RequestParam (defaultValue = "1") Integer page, @RequestParam (defaultValue = "0") Integer min, @RequestParam (defaultValue = "100000") Integer max) {
        List<Product> products = productService.findAllByPriceBetween(min, max);
        List<Product> productsOut = new ArrayList<>();
        if (page < 1) {
            page = 1;
        }
        int start = Math.min((page - 1) * 10, products.size());
        int finish = Math.min((page - 1) * 10 + 10, products.size());
        for (int i = start; i < finish; i++) {
            productsOut.add(products.get(i));
        }
        productsOut.forEach(System.out::println);
        return productsOut;
    }

    @GetMapping("/product/delete/{id}")
    public void delProduct (@PathVariable Long id) {
        productService.delById(id);
    }

    @GetMapping("/products/min")
    public List<Product> getProductsUpByMin (@RequestParam (defaultValue = "0") Integer min) {
        return productService.findAllByPriceBetween(min, 2147483647);
    }

    @GetMapping("/products/max")
    public List<Product> getProductsDownByMax (@RequestParam (defaultValue = "100000") Integer max) {
        return productService.findAllByPriceBetween(0, max);
    }

    @GetMapping("/products/between")
    public List<Product> getProductsBetween (@RequestParam (defaultValue = "0") Integer min, @RequestParam (defaultValue = "100000") Integer max) {
        return productService.findAllByPriceBetween(min, max);
    }

}
