package kubecluster.productmanagementbackend.Service;

import jakarta.annotation.PostConstruct;
import kubecluster.productmanagementbackend.DAO.ProductCategoryRepository;
import kubecluster.productmanagementbackend.DAO.ProductRepository;
import kubecluster.productmanagementbackend.Model.Product;
import kubecluster.productmanagementbackend.Model.ProductCategory;
import kubecluster.productmanagementbackend.Model.ProductDTO;
import kubecluster.productmanagementbackend.Model.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;



    public ResponseEntity<Object> getProducts() {
        try {
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOs = new ArrayList<>();

            for (Product product : products) {
                productDTOs.add(convertToDTO(product));
            }

            return ResponseEntity.ok(productDTOs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                return ResponseEntity.ok().body("Product deleted");
            }
            return ResponseEntity.badRequest().body("Product not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 🛠 Helper Method: Convert Product -> ProductDTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());

        List<ProductCategoryDTO> categoryDTOs = new ArrayList<>();
        if (product.getCategories() != null) {
            for (ProductCategory category : product.getCategories()) {
                ProductCategoryDTO categoryDTO = new ProductCategoryDTO();
                categoryDTO.setId(category.getId());
                categoryDTO.setName(category.getName());
                categoryDTOs.add(categoryDTO);
            }
        }
        productDTO.setCategories(categoryDTOs);
        return productDTO;
    }
}
