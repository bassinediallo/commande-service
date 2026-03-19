package com.groupeisi.commandeservice.dto;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor
public class ProduitDTO {
    private Long id;
    private String nom;
    private String code;
    private String description;
    private Double stock;
    private String niveau;
    private String enseignant;
}
