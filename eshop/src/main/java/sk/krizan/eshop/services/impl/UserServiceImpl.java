package sk.krizan.eshop.services.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import sk.krizan.eshop.domain.User;
import sk.krizan.eshop.mapper.UserRowMapper;
import sk.krizan.eshop.services.api.UserService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        final String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public User getById(Integer id) {
        final String sql = "SELECT * FROM user WHERE id = " + id;
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer add(User user) {
        final String sql = "INSERT INTO user " +
                "(gender, name, email, address, postcode, phoneNumber) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getGender());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getPostcode());
            preparedStatement.setString(6, user.getPhoneNumber());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            return keyHolder.getKey().intValue();
        } else {
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        final String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Integer id, User user) {
        final String sql = "UPDATE user SET gender = ?, name = ?, email = ?, address = ?, postcode = ?, phoneNumber = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getGender(), user.getName(), user.getEmail(), user.getAddress(), user.getPostcode(), user.getPhoneNumber(), id);
    }
}
