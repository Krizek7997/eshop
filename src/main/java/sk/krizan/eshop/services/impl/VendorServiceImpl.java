package sk.krizan.eshop.services.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Service;
import sk.krizan.eshop.domain.Vendor;
import sk.krizan.eshop.mapper.VendorRowMapper;
import sk.krizan.eshop.services.api.VendorService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final JdbcTemplate jdbcTemplate;
    private final VendorRowMapper vendorRowMapper = new VendorRowMapper();

    public VendorServiceImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Vendor> getVendors() {
        final String sql = "SELECT * FROM vendor";
        return jdbcTemplate.query(sql, vendorRowMapper);
    }

    @Override
    public Vendor getById(Integer id) {
        final String sql = "SELECT * FROM vendor WHERE id = " + id;
        try {
            return jdbcTemplate.queryForObject(sql, vendorRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer add(Vendor vendor) {
        final String sql = "INSERT INTO vendor (name, description, address) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, vendor.getName());
            if (vendor.getDescription() != null) {
                preparedStatement.setString(2, vendor.getDescription());
            } else {
                preparedStatement.setNull(2, Types.VARCHAR);
            }
            preparedStatement.setString(3, vendor.getAddress());

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
        final String sql = "DELETE FROM vendor WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Integer id, Vendor vendor) {
        final String sql = "UPDATE vendor SET name = ?, description = ?, address = ? WHERE id = ?";
        jdbcTemplate.update(sql, vendor.getName(), vendor.getDescription(), vendor.getAddress(), id);
    }
}
