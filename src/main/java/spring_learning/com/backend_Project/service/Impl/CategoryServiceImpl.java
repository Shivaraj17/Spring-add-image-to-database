package spring_learning.com.backend_Project.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_learning.com.backend_Project.entity.Category;
import spring_learning.com.backend_Project.exception.ResourceNotFoundException;
import spring_learning.com.backend_Project.payloads.CategoryDto;
import spring_learning.com.backend_Project.repository.CategoryRepo;
import spring_learning.com.backend_Project.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category newCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(newCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());

        Category savedCategory =this.categoryRepo.save(category);

        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> Categorys =this.categoryRepo.findAll();

        List<CategoryDto> CategoryDto = Categorys.stream().map((category)->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());

        return CategoryDto;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }

    @Override
    public String deleteCategory(Integer categoryId) {
        Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
        this.categoryRepo.delete(category);
        return "Category has been deleted";
    }
}
