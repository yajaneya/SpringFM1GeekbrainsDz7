package ru.yajaneya.SpringFM1GeekbrainsDz7.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;

import java.util.ArrayList;
import java.util.List;


@Component
@Data
@NoArgsConstructor
public class Cart {
    private List<CartEntity> cartEntities = new ArrayList<>();

    public CartEntity putProductToCart (Product product) {
        System.out.println(product);
        CartEntity cartEntity = findProductInCart(product);
        System.out.println(cartEntity);
        cartEntity.setQuantity(cartEntity.getQuantity() + 1);
        return cartEntity;
    }

    private CartEntity findProductInCart(Product product) {

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

    private CartEntity createCartEntity (Product product) {
        CartEntity cartEntity = new CartEntity(product.getId(), product.getTitle(), product.getPrice(), 0);
        cartEntities.add(cartEntity);
        return cartEntity;
    }

    public CartEntity deleteProductFromCart(Product product) {
        CartEntity cartEntity = findProductInCart(product);
        if (cartEntity.getQuantity() > 0) {
            cartEntity.setQuantity(cartEntity.getQuantity() - 1);
        }
        return cartEntity;
    }
}
