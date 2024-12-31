package spring_learning.com.backend_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_learning.com.backend_Project.payloads.ApiResponse;
import spring_learning.com.backend_Project.payloads.CategoryDto;
import spring_learning.com.backend_Project.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto CreatedCategoryDto =this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(CreatedCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto UpdatedCategory =this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(UpdatedCategory,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> GetAllCategory(){
        List<CategoryDto> categoryDto =this.categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> GetCategory(@PathVariable Integer categoryId){
        CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category has been successfully deleted",true),HttpStatus.OK);
    }
}
