package sk.krizan.eshop.mapper;

import org.springframework.jdbc.core.RowMapper;
import sk.krizan.eshop.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setGender(resultSet.getString("gender"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setAddress(resultSet.getString("address"));
        user.setPostcode(resultSet.getString("postcode"));
        user.setPhoneNumber(resultSet.getString("phoneNumber"));
        return user;
    }
}
