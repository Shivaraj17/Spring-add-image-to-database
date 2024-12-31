package spring_learning.com.backend_Project.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer id;

    private String categoryTitle;

    private String categoryDescription;
}
