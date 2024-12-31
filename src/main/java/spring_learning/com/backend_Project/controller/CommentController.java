package spring_learning.com.backend_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_learning.com.backend_Project.payloads.ApiResponse;
import spring_learning.com.backend_Project.payloads.CommentDto;
import spring_learning.com.backend_Project.service.CommentService;

@RestController
//@RequestMapping("/api/comment") //befor relationship
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

//    @PostMapping("/") befor mapping
    @PostMapping("/post/{postId}/comments")
//    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){ before mapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
//        CommentDto commentDto1 =this.commentService.createComment(commentDto); before mapping
        CommentDto commentDto1 =this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment has been deleted successfully",true),HttpStatus.OK);
    }
}
