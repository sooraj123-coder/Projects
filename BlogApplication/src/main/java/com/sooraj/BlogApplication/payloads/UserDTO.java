package com.sooraj.BlogApplication.payloads;

import com.sooraj.BlogApplication.entities.User;
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
    private String email;
    private String name;
    private String password;
    private String about;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
        this.about = user.getAbout();
    }
}
