package com.groupeisi.commandeservice.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Table(name = "commandes") @Data @NoArgsConstructor @AllArgsConstructor
public class Commande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private Long etudiantId;
    @Column(nullable = false) private Long produitId;
    @Column(nullable = false) private String nomProduit;
    @Column(nullable = false) private Double quantite;
    @Column(nullable = false) private LocalDateTime dateCommande;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Statut statut;
    public enum Statut { EN_ATTENTE, CONFIRMEE, ANNULEE }
    @PrePersist public void prePersist() {
        this.dateCommande = LocalDateTime.now();
        if (this.statut == null) this.statut = Statut.EN_ATTENTE;
    }
}
