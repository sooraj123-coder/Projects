package com.sooraj.BlogApplication.payloads;

import com.sooraj.BlogApplication.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;
    @Email(message = "Email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min = 4, message = "User name must be of minimum 4 characters !!")
    private String name;

    @NotEmpty
    @Size(min=4,max = 10, message = "User password must not less than 4 and more than 10 characters !!")
    private String password;

    @NotEmpty
    private String about;
}
