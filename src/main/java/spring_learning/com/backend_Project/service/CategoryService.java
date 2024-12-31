package spring_learning.com.backend_Project.service;

import spring_learning.com.backend_Project.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategory(Integer categoryId);

    String deleteCategory(Integer categoryId);
}
