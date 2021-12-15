package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yajaneya.SpringFM1GeekbrainsDz7.cart.Cart;
import ru.yajaneya.SpringFM1GeekbrainsDz7.cart.CartEntity;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor

public class CartController {

    private final Cart cart;

    @GetMapping
    public List <CartEntity> getCart () {
        return cart.getCartEntities();
    }

    @PostMapping
    public CartEntity putToCart (@RequestBody Product product) {
        return cart.putProductToCart(product);
    }

    @DeleteMapping
    public CartEntity deleteFromCart (@RequestBody Product product) {
        return cart.deleteProductFromCart(product);
    }
}
