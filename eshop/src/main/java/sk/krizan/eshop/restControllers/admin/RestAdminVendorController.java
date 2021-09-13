package sk.krizan.eshop.restControllers.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.krizan.eshop.domain.Vendor;
import sk.krizan.eshop.services.api.VendorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin-tools/vendor")
public class RestAdminVendorController {

    private final VendorService vendorService;

    public RestAdminVendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity getAllVendors() {
        List<Vendor> vendors = vendorService.getVendors();
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getByVendorId(@PathVariable("id") Integer id) {
        Vendor vendor = vendorService.getById(id);
        if (vendor != null) {
            return new ResponseEntity<>(vendor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity addVendor(@RequestBody Vendor vendor) {
        Integer id = vendorService.add(vendor);
        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteVendor(@PathVariable("id") Integer id) {
        if (vendorService.getById(id) != null) {
            vendorService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("Vendor with id: " + id + " doesn't exist.");
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity updateVendor(@PathVariable("id") Integer id, @RequestBody Vendor vendor) {
        if (vendorService.getById(id) != null) {
            vendor.setId(id);
            vendorService.update(id, vendor);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("Vendor with id: " + id + " doesn't exist.");
        }
    }

}
