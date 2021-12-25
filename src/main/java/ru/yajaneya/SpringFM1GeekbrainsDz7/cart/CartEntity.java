package ru.yajaneya.SpringFM1GeekbrainsDz7.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartEntity {
    private Long id;
    private String title;
    private int price;
    private int quantity;
}
