package sk.krizan.eshop.restControllers.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.krizan.eshop.domain.Product;
import sk.krizan.eshop.services.api.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin-tools/products")
public class RestAdminProductController {

    private final ProductService productService;

    public RestAdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity getProducts(
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "vendorId", required = false) Integer vendorId,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(name = "size", required = false) String size,
            @RequestParam(name = "color", required = false) String color
    ) {
        List<Product> products;
        if (gender == null) {
            products = productService.getProducts();
        } else {
            products = productService.getProductsByGender(gender);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getByProductId(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product) {
        Integer id = productService.add(product);
        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Integer id) {
        if (productService.getByProductId(id) != null) {
            productService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("Product with id: " + id + " doesn't exist.");
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        if (productService.getByProductId(id) != null) {
            product.setId(id);
            productService.update(id, product);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).
                    body("Product with id: " + id + " doesn't exist.");
        }
    }

}
