package spring_learning.com.backend_Project.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 characters !!")
    private String name;

    @Email(message="Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min=3,max=10,message="Password must be min of 3 char and max of 10 char")
    private String password;

    @NotBlank
    private String about;
}
