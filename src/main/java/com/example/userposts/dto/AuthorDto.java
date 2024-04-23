package com.example.userposts.dto;

import com.example.userposts.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto {
    private String id;
    private String name;

    public AuthorDto(User user) {
        id = user.getId();
        name = user.getName();
    }
}
