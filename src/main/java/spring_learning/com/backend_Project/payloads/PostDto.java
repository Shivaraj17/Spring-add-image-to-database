package spring_learning.com.backend_Project.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_learning.com.backend_Project.entity.Comment;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    //to show above and below in list
    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
