package com.groupeisi.commandeservice.dto;
import lombok.*;
import java.time.LocalDateTime;
@Data @NoArgsConstructor @AllArgsConstructor
public class CommandeDTO {
    private Long id;
    private Long etudiantId;
    private Long produitId;
    private String nomProduit;
    private Double quantite;
    private LocalDateTime dateCommande;
    private String statut;
}
