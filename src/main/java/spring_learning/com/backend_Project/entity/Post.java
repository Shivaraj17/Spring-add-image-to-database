package spring_learning.com.backend_Project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Table(name="post")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name="post_title",length = 100,nullable = false)
    private String title;

    @Column(length = 10000)
    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    Set<Comment> comments = new HashSet<>();
}