package sk.krizan.eshop.restControllers.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.krizan.eshop.domain.Category;
import sk.krizan.eshop.services.api.CategoryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin-tools/category")
public class RestAdminCategoryController {

    private final CategoryService categoryService;

    public RestAdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity getAllCategories() {
        List<Category> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody Category category) {
        Integer id = categoryService.add(category);
        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Integer id) {
        if (categoryService.getById(id) != null) {
            categoryService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("Category with id: " + id + " doesn't exist.");
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        if (categoryService.getById(id) != null) {
            category.setId(id);
            categoryService.update(id, category);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).
                    body("Category with id: " + id + " doesn't exist.");
        }
    }

}
