package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;


import org.springframework.web.bind.annotation.RequestParam;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.ProductService;

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

    @GetMapping("/product/delete/{id}")
    public void delProduct (@PathVariable Long id) {
        productService.delById(id);
    }

    @GetMapping("/products/min")
    public List<Product> getProductsUpByMin (@RequestParam (defaultValue = "0") Integer min) {
        return productService.findAllByPriceGreaterThan(min);
    }

    @GetMapping("/products/max")
    public List<Product> getProductsDownByMax (@RequestParam (defaultValue = "100000") Integer max) {
        return productService.findAllByPriceLessThan(max);
    }

    @GetMapping("/products/between")
    public List<Product> getProductsBetween (@RequestParam (defaultValue = "0") Integer min, @RequestParam (defaultValue = "100000") Integer max) {
        return productService.findAllByPriceBetween(min, max);
    }

}
