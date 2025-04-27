package kubecluster.productmanagementbackend.Controller;

import kubecluster.productmanagementbackend.Model.Product;
import kubecluster.productmanagementbackend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

}
