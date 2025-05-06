package com.zimsec.Security.UserData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserResponseDto {
    private String email;
    private String firstname;

    public UserResponseDto(String firstName, String email) {
        this.firstname = firstName;
        this.email = email;
    }
}
