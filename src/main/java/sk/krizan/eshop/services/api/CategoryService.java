package sk.krizan.eshop.services.api;

import sk.krizan.eshop.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();
    Category getById(Integer id);
    Integer add(Category category);
    void delete(Integer id);
    void update(Integer id, Category category);

}
