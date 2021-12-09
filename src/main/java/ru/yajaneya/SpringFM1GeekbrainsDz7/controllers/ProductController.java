package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;


import org.springframework.data.domain.Page;
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

    @GetMapping("/product/{id}")
    public Product getProductById (@PathVariable Long id) {
        return productService.findByID(id).get();
    }

    @GetMapping("/products")
    public List<Product> getProducts () {
        return productService.findAll();
    }

    @GetMapping("/products/between/page")
    public Page<Product> getBetweenPage
            (@RequestParam (defaultValue = "1") Integer page,
             @RequestParam (name = "min_price", required = false) Integer minPrice,
             @RequestParam (name = "max_price", required = false) Integer maxPrice) {

        if (page < 1) {
            page=1;
        }

        int totalPages = productService.find(minPrice, maxPrice, page).getTotalPages();

        if (page > totalPages) {
            page = totalPages;
        }

        return productService.find(minPrice, maxPrice, page);
    }

    @GetMapping("/product/delete/{id}")
    public void delProduct (@PathVariable Long id) {
        productService.delById(id);
    }

}
