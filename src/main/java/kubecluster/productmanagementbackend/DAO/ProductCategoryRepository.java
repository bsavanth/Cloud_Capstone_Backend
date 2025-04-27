package kubecluster.productmanagementbackend.DAO;

import kubecluster.productmanagementbackend.Model.Product;
import kubecluster.productmanagementbackend.Model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByName(String name);
}
