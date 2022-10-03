package com.mindhub.finalProject.Services.Implements;

import com.mindhub.finalProject.Services.CategoryService;
import com.mindhub.finalProject.models.Category;
import com.mindhub.finalProject.Services.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCategory(Category category) {
    categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
    categoryRepository.delete(category);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }
}
