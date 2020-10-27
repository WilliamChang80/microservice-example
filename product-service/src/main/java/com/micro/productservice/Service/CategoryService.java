package com.micro.productservice.Service;


import com.micro.productservice.Dto.CategoryRequest;
import com.micro.productservice.Entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    void createCategory(CategoryRequest request);
}
