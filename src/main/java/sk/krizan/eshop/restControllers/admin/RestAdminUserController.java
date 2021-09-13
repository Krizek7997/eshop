package sk.krizan.eshop.restControllers.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.krizan.eshop.domain.User;
import sk.krizan.eshop.services.api.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin-tools/user")
public class RestAdminUserController {

    private final UserService userService;

    public RestAdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getByUserId(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
        Integer id = userService.add(user);
        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Integer id) {
        if (userService.getById(id) != null) {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("User with id: " + id + " doesn't exist.");
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        if (userService.getById(id) != null) {
            user.setId(id);
            userService.update(id, user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("User with id: " + id + " doesn't exist.");
        }
    }

}
