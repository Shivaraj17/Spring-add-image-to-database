package spring_learning.com.backend_Project.service;

import spring_learning.com.backend_Project.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUsers(UserDto userDto);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto, Integer userId);

    String deleteUser(Integer userId);
}
