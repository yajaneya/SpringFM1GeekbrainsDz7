package ru.yajaneya.SpringFM1GeekbrainsDz7.services;

import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import org.springframework.stereotype.Service;
import ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findByID (Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll () {
        return productRepository.findAll();
    }

    public void delById (Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByPriceGreaterThan (Integer min) {
        return productRepository.findAllByPriceGreaterThan(min);
    }

    public List<Product> findAllByPriceLessThan (Integer max) {
        return productRepository.findAllByPriceLessThan(max);
    }
    public List<Product> findAllByPriceBetween (Integer min, Integer max) {
        return productRepository.findAllByPriceBetween(min, max);
    }
}