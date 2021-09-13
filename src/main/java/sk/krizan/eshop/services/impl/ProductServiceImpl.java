package sk.krizan.eshop.services.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import sk.krizan.eshop.domain.Product;
import sk.krizan.eshop.mapper.ProductRowMapper;
import sk.krizan.eshop.services.api.ProductService;

import java.sql.*;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final JdbcTemplate jdbcTemplate;
    private final ProductRowMapper productRowMapper = new ProductRowMapper();

    public ProductServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getProducts() {
        final String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    /*
    @Override
    public List<Product> getProductsByCategoryId(String gender, Integer categoryId) {
        final String sql = "SELECT * FROM product WHERE gender = " + gender +
                " AND categoryID = " + categoryId;
        try {
            return jdbcTemplate.query(sql, productRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByVendorId(String gender, Integer vendorId) {
        final String sql = "SELECT * FROM product WHERE gender = " + gender +
                " AND vendorID = " + vendorId;
        try {
            return jdbcTemplate.query(sql, productRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    */

    @Override
    public List<Product> getProductsByGender(String gender) {
        final String sql = "SELECT * FROM product WHERE gender = " + gender;
        try {
            return jdbcTemplate.query(sql, productRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Product getByProductId(Integer id) {
        final String sql = "SELECT * FROM product WHERE id = " + id;
        try {
            return jdbcTemplate.queryForObject(sql, productRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer add(Product product) {
        final String sql = "INSERT INTO product " +
                "(gender, name, description, size, prize, amountInStock, categoryID, vendorID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, product.getGender());
            preparedStatement.setString(2, product.getName());
            if (product.getDescription() != null) {
                preparedStatement.setString(3, product.getDescription());
            } else {
                preparedStatement.setNull(3, Types.VARCHAR);
            }
            preparedStatement.setString(4, product.getSize());
            preparedStatement.setDouble(5, product.getPrize());
            preparedStatement.setInt(6, product.getAmountInStock());
            preparedStatement.setInt(7, product.getCategoryId());
            preparedStatement.setInt(8, product.getVendorId());

            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().intValue();
        } else {
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        final String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Integer id, Product product) {
        final String sql = "UPDATE product SET " +
                "gender = ?, name = ?, description = ?, size = ?, " +
                "prize = ?, amountInStock = ?, categoryID = ?, vendorID = ? WHERE id = ?";
        jdbcTemplate.update
                (sql, product.getGender(), product.getName(), product.getDescription(),
                        product.getSize(), product.getPrize(), product.getAmountInStock(),
                        product.getCategoryId(), product.getVendorId(), id);
    }
}
