package sk.krizan.eshop.restControllers.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sk.krizan.eshop.domain.Product;
import sk.krizan.eshop.services.api.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/{gender}/products")
public class RestFeedController {

    private final ProductService productService;

    public RestFeedController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity getProductsByGender(@PathVariable("gender") String gender) {
        List<Product> products = productService.getProductsByGender(gender);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
