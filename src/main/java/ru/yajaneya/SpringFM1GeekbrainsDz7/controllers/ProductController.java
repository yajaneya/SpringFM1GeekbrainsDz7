package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.ProductDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getProducts
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

        return productService.find(minPrice, maxPrice, page).map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById (@PathVariable Long id) {
        return new ProductDto(productService.findByID(id).get());
    }

    @PostMapping
    public ProductDto saveNewProduct (@RequestBody ProductDto productDto) {
        Product product = new Product(productDto);
        product.setId(null);
        product = productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping
    public ProductDto updateProducrt (@RequestBody ProductDto productDto) {
        Product product = new Product(productDto);
        product = productService.save(product);
        return new ProductDto(product);
    }

    @DeleteMapping("/{id}")
    public void delProduct (@PathVariable Long id) {
        productService.delById(id);
    }

}
