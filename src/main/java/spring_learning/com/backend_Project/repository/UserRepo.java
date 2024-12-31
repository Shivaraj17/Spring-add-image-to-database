package spring_learning.com.backend_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_learning.com.backend_Project.entity.Category;
import spring_learning.com.backend_Project.entity.Post;
import spring_learning.com.backend_Project.entity.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {

}
