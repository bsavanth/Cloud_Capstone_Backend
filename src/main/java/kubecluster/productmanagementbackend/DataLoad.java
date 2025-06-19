package kubecluster.productmanagementbackend;

import jakarta.annotation.PostConstruct;
import kubecluster.productmanagementbackend.DAO.ProductCategoryRepository;
import kubecluster.productmanagementbackend.DAO.ProductRepository;
import kubecluster.productmanagementbackend.Model.Product;
import kubecluster.productmanagementbackend.Model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoad {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @PostConstruct
    public void init() {
        initCategories();
        initProducts();
    }
    public void initProducts() {
        if (productRepository.count() == 0) {
            // Fetch some existing categories first
            List<ProductCategory> allCategories = productCategoryRepository.findAll();

            if (allCategories.isEmpty()) {
                System.out.println("No categories found. Skipping product creation.");
                return;
            }

            // Create some sample Products
            List<Product> defaultProducts = new ArrayList<>();

            Product phone = new Product();
            phone.setName("iPhone 15");
            phone.setDescription("Latest Apple smartphone");
            phone.setQuantity(100);
            phone.setPrice(999.99);
            phone.setCategories(List.of(
                    productCategoryRepository.findByName("Electronics")
                            .orElseThrow(() -> new RuntimeException("Category 'Electronics' not found"))
            ));

            Product tshirt = new Product();
            tshirt.setName("Nike T-Shirt");
            tshirt.setDescription("Breathable sports t-shirt");
            tshirt.setQuantity(200);
            tshirt.setPrice(29.99);
            tshirt.setCategories(List.of(
                    productCategoryRepository.findByName("Clothing")
                            .orElseThrow(() -> new RuntimeException("Category 'Clothing' not found"))
            ));

            Product novel = new Product();
            novel.setName("Atomic Habits");
            novel.setDescription("Best-selling book on habit building");
            novel.setQuantity(150);
            novel.setPrice(15.99);
            novel.setCategories(List.of(
                    productCategoryRepository.findByName("Books")
                            .orElseThrow(() -> new RuntimeException("Category 'Books' not found"))
            ));

            defaultProducts.add(phone);
            defaultProducts.add(tshirt);
            defaultProducts.add(novel);

            productRepository.saveAll(defaultProducts);
            System.out.println("Default Products inserted!");
        }
    }


    private void initCategories() {
        if (productCategoryRepository.count() == 0) { // Only insert if empty
            List<ProductCategory> defaultCategories = Arrays.asList(
                    new ProductCategory(null, "Electronics"),
                    new ProductCategory(null, "Books"),
                    new ProductCategory(null, "Clothing"),
                    new ProductCategory(null, "Home & Kitchen"),
                    new ProductCategory(null, "Beauty & Personal Care"),
                    new ProductCategory(null, "Sports & Outdoors"),
                    new ProductCategory(null, "Toys & Games"),
                    new ProductCategory(null, "Automotive"),
                    new ProductCategory(null, "Garden & Outdoor"),
                    new ProductCategory(null, "Health & Household"),
                    new ProductCategory(null, "Apple Products")
            );

            productCategoryRepository.saveAll(defaultCategories);
            productCategoryRepository.flush(); // Keep this
            System.out.println("Default Product Categories inserted!");
        }
    }
}
