package com.groupeisi.commandeservice.repository;
import com.groupeisi.commandeservice.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByEtudiantId(Long etudiantId);
    List<Commande> findByProduitId(Long produitId);
}
