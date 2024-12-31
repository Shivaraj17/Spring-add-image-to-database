package spring_learning.com.backend_Project.service;

import spring_learning.com.backend_Project.payloads.CommentDto;

public interface CommentService {

//    CommentDto createComment(CommentDto commentDto); before mapping
    CommentDto createComment(CommentDto commentDto,Integer postId);

    String deleteComment(Integer commentId);
}
