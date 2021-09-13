package sk.krizan.eshop.mapper;


import org.springframework.jdbc.core.RowMapper;
import sk.krizan.eshop.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setGender(resultSet.getString("gender"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setSize(resultSet.getString("size"));
        product.setPrize(resultSet.getDouble("prize"));
        product.setAmountInStock(resultSet.getInt("amountInStock"));
        product.setCategoryId(resultSet.getInt("categoryID"));
        product.setVendorId(resultSet.getInt("vendorID"));
        return product;
    }
}
