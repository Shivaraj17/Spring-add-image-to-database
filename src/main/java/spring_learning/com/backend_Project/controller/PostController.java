package spring_learning.com.backend_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_learning.com.backend_Project.entity.Post;
import spring_learning.com.backend_Project.payloads.*;
import spring_learning.com.backend_Project.service.PostService;
import spring_learning.com.backend_Project.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;



    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto ,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ){
        PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> upadtePost(@RequestBody PostDto postDto ,@PathVariable Integer postId){
        PostDto updatedPost = this.postService.upadtePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

//    @GetMapping("/")  before pagination and sorting
//    public ResponseEntity<List<PostDto>> getAllPost(){
//        List<PostDto> postDtos = this.postService.getAllPost();
//        return new ResponseEntity<>(postDtos,HttpStatus.OK);
//    }

    @GetMapping("/posts")
    //to get data in object not in list
//    public ResponseEntity<List<PostDto>> getAllPost(
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        //to get data in object not in list
//       List<PostDto> postDtos = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto>getPost(@PathVariable Integer postId){
        PostDto postdto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postdto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.DeletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post has been deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> posts =this.postService.getPostByUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> categorys= this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(categorys,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{title}")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable String title){
        List<PostDto> result =this.postService.searchPosts(title);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
