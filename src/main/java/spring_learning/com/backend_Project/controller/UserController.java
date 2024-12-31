package spring_learning.com.backend_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_learning.com.backend_Project.entity.User;
import spring_learning.com.backend_Project.payloads.ApiResponse;
import spring_learning.com.backend_Project.payloads.UserDto;
import spring_learning.com.backend_Project.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUsers(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUsers(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUsers(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updatedUser = this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUsers(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User has been deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
        UserDto userDto = this.userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

}
