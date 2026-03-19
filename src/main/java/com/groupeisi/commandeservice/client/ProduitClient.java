package com.groupeisi.commandeservice.client;
import com.groupeisi.commandeservice.dto.ProduitDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

// Communication SYNCHRONE : commande-service → produit-service
// Avant d'ajouter une commande, on vérifie le produit et on diminue le stock
@FeignClient(name = "produit-service", url = "${produit.service.url}")
public interface ProduitClient {
    @GetMapping("/api/produits/{id}")
    ProduitDTO findById(@PathVariable Long id);

    @PutMapping("/api/produits/{id}/diminuer-stock")
    ProduitDTO diminuerStock(@PathVariable Long id, @RequestParam Double quantite);
}
