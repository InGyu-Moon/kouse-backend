package org.example.kushousebackend.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {
    private String username;
    private String password;
    private String nickname;
    private String role;
}
