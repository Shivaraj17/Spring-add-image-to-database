package spring_learning.com.backend_Project.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_learning.com.backend_Project.entity.Comment;
import spring_learning.com.backend_Project.entity.Post;
import spring_learning.com.backend_Project.exception.ResourceNotFoundException;
import spring_learning.com.backend_Project.payloads.CommentDto;
import spring_learning.com.backend_Project.repository.CommentRepo;
import spring_learning.com.backend_Project.repository.PostRepo;
import spring_learning.com.backend_Project.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        //relationship mapping
        comment.setPost(post);

        Comment newComment = this.commentRepo.save(comment);
        return this.modelMapper.map(newComment,CommentDto.class);
    }

    @Override
    public String deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","CommentId",commentId));
        this.commentRepo.delete(comment);
        return "Comment has been deleted";
    }
}
