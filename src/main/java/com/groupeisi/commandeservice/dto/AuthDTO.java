package com.groupeisi.commandeservice.dto;
import lombok.*;
public class AuthDTO {
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class RegisterRequest { private String email; private String password; private String role; }
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class LoginRequest { private String email; private String password; }
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class LoginResponse { private String token; private String type = "Bearer"; private Long userId; private String email; private String role; }
}
