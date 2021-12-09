package ru.yajaneya.SpringFM1GeekbrainsDz7.repositories;

import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

    List<Product> findAllByPriceBetween (Integer min, Integer max);

}
