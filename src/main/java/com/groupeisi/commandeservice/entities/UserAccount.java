package com.groupeisi.commandeservice.entities;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "users") @Data @NoArgsConstructor @AllArgsConstructor
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String email;
    @Column(nullable = false) private String password;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Role role;
    public enum Role { USER, ADMIN }
}
