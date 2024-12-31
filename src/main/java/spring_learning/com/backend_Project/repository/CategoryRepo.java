package spring_learning.com.backend_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_learning.com.backend_Project.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
