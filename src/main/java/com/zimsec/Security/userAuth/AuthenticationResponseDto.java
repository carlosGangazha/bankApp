package com.zimsec.Security.userAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class AuthenticationResponseDto {
    private final String token;

    public AuthenticationResponseDto(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
