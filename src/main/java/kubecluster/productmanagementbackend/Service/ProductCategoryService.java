package kubecluster.productmanagementbackend.Service;

import kubecluster.productmanagementbackend.DAO.ProductCategoryRepository;
import kubecluster.productmanagementbackend.Model.ProductCategory;
import kubecluster.productmanagementbackend.Model.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    public ResponseEntity<Object> getProductCategories()
    {
        try{
            List<ProductCategory> productCategories = productCategoryRepository.findAll();
            return ResponseEntity.ok(convertToDTOs(productCategories));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }

    }



    public ResponseEntity<String> deleteProductCategory(Long id)
    {
        try{
            if(productCategoryRepository.existsById(id)){
                productCategoryRepository.deleteById(id);
                return ResponseEntity.ok("Product with "+ id +"deleted.");
            }
            return ResponseEntity.badRequest().body("Product Category Not Found");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }

    private List<ProductCategoryDTO> convertToDTOs(List<ProductCategory> productCategories) {
        List<ProductCategoryDTO> productCategoryDTOs = new ArrayList<>();
        for(ProductCategory productCategory : productCategories){
            ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
            productCategoryDTO.setId(productCategory.getId());
            productCategoryDTO.setName(productCategory.getName());
            productCategoryDTOs.add(productCategoryDTO);
        }
        return productCategoryDTOs;
    }

}
