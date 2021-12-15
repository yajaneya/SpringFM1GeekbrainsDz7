package ru.yajaneya.SpringFM1GeekbrainsDz7.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.ProductDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;

import java.util.ArrayList;
import java.util.List;


@Component
@Data
@NoArgsConstructor
public class Cart {
    private List<CartEntity> cartEntities = new ArrayList<>();

    public CartEntity putProductToCart (ProductDto product) {
        System.out.println(product);
        CartEntity cartEntity = findProductInCart(product);
        System.out.println(cartEntity);
        cartEntity.setQuantity(cartEntity.getQuantity() + 1);
        return cartEntity;
    }

    private CartEntity findProductInCart(ProductDto product) {

        if (cartEntities == null) {
            return createCartEntity(product);
        }

        for (CartEntity cartEntity : cartEntities) {
            if (cartEntity.getId() == product.getId()) {
                return cartEntity;
            }
        }

        return createCartEntity(product);
    }

    private CartEntity createCartEntity (ProductDto product) {
        CartEntity cartEntity = new CartEntity(product.getId(), product.getTitle(), product.getPrice(), 0);
        cartEntities.add(cartEntity);
        return cartEntity;
    }

    public CartEntity deleteProductFromCart(ProductDto product) {
        CartEntity cartEntity = findProductInCart(product);
        if (cartEntity.getQuantity() > 0) {
            cartEntity.setQuantity(cartEntity.getQuantity() - 1);
        }
        if (cartEntity.getQuantity() == 0) {
            cartEntities.remove(cartEntity);
        }
        return cartEntity;
    }

    public CartEntity addProductToCart(ProductDto product) {
        CartEntity cartEntity = findProductInCart(product);
        cartEntity.setQuantity(cartEntity.getQuantity() + 1);
        return cartEntity;
    }

    public int getSumInCart() {
        int sum = 0;
        if (cartEntities == null) {
            return sum;
        }

        for (CartEntity cartEntity : cartEntities) {
            sum += cartEntity.getQuantity() * cartEntity.getPrice();
        }
        return sum;
    }
}
