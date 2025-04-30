package com.jwt.jwt.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;

    // public TokenResponse(String accessToken) {
    //     this.accessToken = accessToken;
    // }

    // // Getter and setter
    // public String getToken() {
    //     return accessToken;
    // }

    // public void setToken(String accessToken) {
    //     this.accessToken = accessToken;
    // }
}
