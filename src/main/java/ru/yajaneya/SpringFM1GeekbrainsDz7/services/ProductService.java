package ru.yajaneya.SpringFM1GeekbrainsDz7.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import org.springframework.stereotype.Service;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.AppError;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.ResourceNotFoundException;
import ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.ProductRepository;
import ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.specifications.ProductSpecifications;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> find (Integer minPrice, Integer maxPrice, Integer page) {

        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }

    public Optional<Product> findByID (Long id) {
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id=" + id + " not found")));
    }

    public Product save (Product product) {
        return productRepository.save(product);
    }

    public void delById (Long id) {
        productRepository.deleteById(id);
    }

}
