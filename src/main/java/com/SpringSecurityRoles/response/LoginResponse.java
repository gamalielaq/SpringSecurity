package com.SpringSecurityRoles.response;

import java.time.LocalDateTime;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String username;
    private String roles;
    private String[] authorities;
    private LocalDateTime expiresAt;

    // Constructor
    public LoginResponse(String accessToken, String refreshToken, String username, 
        String roles, String[] authorities, LocalDateTime expiresAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.roles = roles;
        this.authorities = authorities;
        this.expiresAt = expiresAt;
    }

    // Getters y Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
