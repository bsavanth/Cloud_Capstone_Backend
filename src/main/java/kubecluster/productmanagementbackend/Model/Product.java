package kubecluster.productmanagementbackend.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@Table(name = "PRODUCT")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    @JoinTable(
            name = "product_category_mapping",
            joinColumns = @JoinColumn(name = "product_id"),         // Correct FK column for Product
            inverseJoinColumns = @JoinColumn(name = "category_id")   // Correct FK column for Category
    )
    private List<ProductCategory> categories;


    private String name;
    private String description;
    private int quantity;
    private double price;

}
