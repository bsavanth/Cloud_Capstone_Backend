package kubecluster.productmanagementbackend.Controller;


import kubecluster.productmanagementbackend.Service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/product-category")
@RestController
public class ProductCategoryController {


    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/getProductCategories")
    public ResponseEntity<Object> getProductCategories()
    {
        return productCategoryService.getProductCategories();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductCategory(@PathVariable("id") Long id)
    {
        return productCategoryService.deleteProductCategory(id);
    }


}
