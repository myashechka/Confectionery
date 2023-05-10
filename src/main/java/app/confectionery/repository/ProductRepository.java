package app.confectionery.repository;

import app.confectionery.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getAllByVisible(Boolean visible);
}
