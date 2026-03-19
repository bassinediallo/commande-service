package com.groupeisi.commandeservice.kafka;
import lombok.*;
import java.time.LocalDateTime;
// Événement envoyé via Kafka au paiement-service
@Data @NoArgsConstructor @AllArgsConstructor
public class CommandeEvent {
    private Long commandeId;
    private Long etudiantId;
    private Long produitId;
    private String nomProduit;
    private Double quantite;
    private LocalDateTime dateCommande;
    private String statut;
}
