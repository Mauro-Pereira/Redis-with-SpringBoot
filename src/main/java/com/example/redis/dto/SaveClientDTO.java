package com.example.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveClientDTO {
       
    private String name;
    private String surname;
    private String email;
    private String password;

}
