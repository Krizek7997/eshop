package sk.krizan.eshop.mapper;

import org.springframework.jdbc.core.RowMapper;
import sk.krizan.eshop.domain.Vendor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorRowMapper implements RowMapper<Vendor> {
    @Override
    public Vendor mapRow(ResultSet resultSet, int i) throws SQLException {
        Vendor vendor = new Vendor();
        vendor.setId(resultSet.getInt("id"));
        vendor.setName(resultSet.getString("name"));
        vendor.setDescription(resultSet.getString("description"));
        vendor.setAddress(resultSet.getString("address"));
        return vendor;
    }
}
