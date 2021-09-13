package sk.krizan.eshop.services.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import sk.krizan.eshop.domain.Category;
import sk.krizan.eshop.mapper.CategoryRowMapper;
import sk.krizan.eshop.services.api.CategoryService;
import java.sql.PreparedStatement;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final JdbcTemplate jdbcTemplate;
    private final CategoryRowMapper categoryRowMapper = new CategoryRowMapper();

    public CategoryServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> getCategories() {
        final String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, categoryRowMapper);
    }

    @Override
    public Category getById(Integer id) {
        final String sql = "SELECT * FROM category WHERE id = " + id;
        try {
            return jdbcTemplate.queryForObject(sql, categoryRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer add(Category category) {
        final String sql = "INSERT INTO category (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
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
        final String sql = "DELETE FROM category WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Integer id, Category category) {
        final String sql = "UPDATE category SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, category.getName(), id);
    }
}
