package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yajaneya.SpringFM1GeekbrainsDz7.cart.Cart;
import ru.yajaneya.SpringFM1GeekbrainsDz7.cart.CartEntity;
import ru.yajaneya.SpringFM1GeekbrainsDz7.converters.ProductConverter;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.ProductDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.ResourceNotFoundException;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor

public class CartController {

    private final Cart cart;
    private final ProductController productController;

    @GetMapping
    public List <CartEntity> getCart () {
        return cart.getCartEntities();
    }

    @PostMapping
    public CartEntity putToCart (@RequestBody Long id) {
        ProductDto product = productController.getProductById(id);
        return cart.putProductToCart(product);
    }

    @PutMapping
    public CartEntity addToCart (@RequestBody Long id) {
        ProductDto product = productController.getProductById(id);
        return cart.addProductToCart(product);
    }

    @DeleteMapping("/{id}")
    public CartEntity deleteFromCart (@PathVariable Long id) {
        ProductDto product = productController.getProductById(id);
        return cart.deleteProductFromCart(product);
    }

    @GetMapping("/sum")
    public int getSumInCart () {
        return cart.getSumInCart();
    }
}
