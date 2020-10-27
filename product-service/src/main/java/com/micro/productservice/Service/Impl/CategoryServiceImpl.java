package com.micro.productservice.Service.Impl;

import com.micro.productservice.Dto.CategoryRequest;
import com.micro.productservice.Entity.Category;
import com.micro.productservice.Repository.CategoryRepository;
import com.micro.productservice.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(CategoryRequest request) {
        Category category = Category.builder().name(request.getName()).build();
        categoryRepository.save(category);
    }
}
