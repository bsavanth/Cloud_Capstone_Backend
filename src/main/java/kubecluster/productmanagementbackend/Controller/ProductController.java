package kubecluster.productmanagementbackend.Controller;


import kubecluster.productmanagementbackend.Model.Product;
import kubecluster.productmanagementbackend.Service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProducts")
    public ResponseEntity<Object> getProducts()
    {
        return productService.getProducts();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id)
    {
       return productService.deleteProduct(id);
    }

    @GetMapping("sortedproducts")
    public ResponseEntity<Object> sortedProducts(@RequestParam String order, String param){

        return productService.sortedProducts(order, param);


    }

}
