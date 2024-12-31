package spring_learning.com.backend_Project.service;

import spring_learning.com.backend_Project.payloads.PostDto;
import spring_learning.com.backend_Project.payloads.PostResponse;
import spring_learning.com.backend_Project.payloads.UserDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);

    PostDto upadtePost(PostDto postDto,Integer postId);

//    List<PostDto> getAllPost() before sorting
    //By default Jpa Repository have default pageNumber and sorting
//    List<PostDto> getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);

    //return type is change to PostResponse to get data in object
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDto getPostById(Integer postId);

    String DeletePost(Integer postId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> searchPosts(String title);
}
