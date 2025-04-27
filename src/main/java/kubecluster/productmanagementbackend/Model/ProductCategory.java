package kubecluster.productmanagementbackend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Table(name = "PRODUCT_CATEGORY")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    List<Product> products;

    public ProductCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
