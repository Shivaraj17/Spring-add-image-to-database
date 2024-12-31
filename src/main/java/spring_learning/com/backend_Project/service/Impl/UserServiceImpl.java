package spring_learning.com.backend_Project.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_learning.com.backend_Project.entity.User;
import spring_learning.com.backend_Project.exception.ResourceNotFoundException;
import spring_learning.com.backend_Project.payloads.UserDto;
import spring_learning.com.backend_Project.repository.UserRepo;
import spring_learning.com.backend_Project.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUsers(UserDto userDto) {

        User user = this.DtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.UserToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        return this.UserToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users =this.userRepo.findAll();
        List<UserDto> userDto = users.stream().map((user)->this.UserToDto(user)).collect(Collectors.toList());
        return userDto;
    }



    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        UserDto updatedDto = this.UserToDto(updatedUser);
        return updatedDto;
    }

    @Override
    public String deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        this.userRepo.delete(user);
        return "User has been deleted";
    }

    public User DtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto UserToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
