package com.sistema.examenes.Entity;

public class JwtResponse {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse() {

    }

    // Getter y Setter...
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
