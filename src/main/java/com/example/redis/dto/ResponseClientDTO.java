package com.example.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClientDTO {

    private String name;
    private String surname;
    private String email;
    private boolean isAdmin;

}
