package spring_learning.com.backend_Project.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring_learning.com.backend_Project.entity.Category;
import spring_learning.com.backend_Project.entity.Post;
import spring_learning.com.backend_Project.entity.User;
import spring_learning.com.backend_Project.exception.ResourceNotFoundException;
import spring_learning.com.backend_Project.payloads.PostDto;
import spring_learning.com.backend_Project.payloads.PostResponse;
import spring_learning.com.backend_Project.repository.CategoryRepo;
import spring_learning.com.backend_Project.repository.PostRepo;
import spring_learning.com.backend_Project.repository.UserRepo;
import spring_learning.com.backend_Project.service.PostService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;



    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));

        Category category =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto upadtePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);

        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
            //before postResponse the data where in list
//    public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize, String sortBy,String sortDir) {

    //after page response to get return type of pageResponse in object
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() :Sort.by(sortBy).descending();

        Pageable p =PageRequest.of(pageNumber,pageSize,sort);

//        List<Post> AllPost = this.postRepo.findAll(); before pagination

        Page<Post> pagePost = this.postRepo.findAll(p);



        List<Post> allPost = pagePost.getContent();

        //before pagination
//        List<PostDto> PostDto =AllPost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        List<PostDto> PostDto =allPost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        //from above used Page we can use below methods

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(PostDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

//        return PostDto; after pageResponse to get response in object
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));

        PostDto postDto = this.modelMapper.map(post,PostDto.class);

        return postDto;
    }

    @Override
    public String DeletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        this.postRepo.delete(post);
        return "Post has been deleted";
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post> posts= this.postRepo.findByUser(user);

        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Post> posts= this.postRepo.findByCategory(category);
        List<PostDto> postDtos =posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String title) {
        List<Post> posts =this.postRepo.findByTitleContaining(title);
        List<PostDto> searchPost = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return searchPost;
    }

}
