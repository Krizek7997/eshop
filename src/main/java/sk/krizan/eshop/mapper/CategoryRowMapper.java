package sk.krizan.eshop.mapper;

import org.springframework.jdbc.core.RowMapper;
import sk.krizan.eshop.domain.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
