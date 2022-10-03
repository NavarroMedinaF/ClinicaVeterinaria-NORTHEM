package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findCategories();

    Category findCategoryById(Long id);

    void saveCategory(Category category);

    void deleteCategory(Category category);

    Category findCategoryByName(String name);
}
