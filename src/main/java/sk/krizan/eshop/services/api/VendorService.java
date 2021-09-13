package sk.krizan.eshop.services.api;

import sk.krizan.eshop.domain.Vendor;

import java.util.List;

public interface VendorService {

    List<Vendor> getVendors();
    Vendor getById(Integer id);
    Integer add(Vendor vendor);
    void delete(Integer id);
    void update(Integer id, Vendor vendor);

}
