package com.sistema.examenes.Entity;

public class JwtRequest {

    private String username;
    private String password;

    public JwtRequest() {

    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter y Setter
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
