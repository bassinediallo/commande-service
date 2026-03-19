package com.groupeisi.commandeservice.service;
import com.groupeisi.commandeservice.client.ProduitClient;
import com.groupeisi.commandeservice.dto.*;
import com.groupeisi.commandeservice.entities.Commande;
import com.groupeisi.commandeservice.exception.ResourceNotFoundException;
import com.groupeisi.commandeservice.kafka.*;
import com.groupeisi.commandeservice.mapper.CommandeMapper;
import com.groupeisi.commandeservice.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service @RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final ProduitClient produitClient;         // communication SYNCHRONE
    private final CommandeProducer commandeProducer;   // communication ASYNCHRONE

    @Cacheable("commandes")
    public List<CommandeDTO> findAll() { return commandeRepository.findAll().stream().map(commandeMapper::toDTO).collect(Collectors.toList()); }

    public CommandeDTO findById(Long id) {
        return commandeMapper.toDTO(commandeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée: " + id)));
    }

    public List<CommandeDTO> findByEtudiant(Long etudiantId) {
        return commandeRepository.findByEtudiantId(etudiantId).stream().map(commandeMapper::toDTO).collect(Collectors.toList());
    }

    @CacheEvict(value="commandes", allEntries=true)
    public CommandeDTO passerCommande(CommandeDTO dto) {
        // 1. Communication SYNCHRONE → produit-service : vérifier le produit et diminuer le stock
        ProduitDTO produit = produitClient.diminuerStock(dto.getProduitId(), dto.getQuantite());

        // 2. Créer la commande
        Commande commande = new Commande();
        commande.setEtudiantId(dto.getEtudiantId());
        commande.setProduitId(produit.getId());
        commande.setNomProduit(produit.getNom());
        commande.setQuantite(dto.getQuantite());
        commande.setStatut(Commande.Statut.CONFIRMEE);
        Commande saved = commandeRepository.save(commande);

        // 3. Communication ASYNCHRONE → Kafka → paiement-service
        CommandeEvent event = new CommandeEvent(
            saved.getId(), saved.getEtudiantId(), saved.getProduitId(),
            saved.getNomProduit(), saved.getQuantite(),
            saved.getDateCommande(), saved.getStatut().name()
        );
        commandeProducer.envoyerCommande(event);

        return commandeMapper.toDTO(saved);
    }

    @CacheEvict(value="commandes", allEntries=true)
    public void annuler(Long id) {
        Commande c = commandeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée: " + id));
        c.setStatut(Commande.Statut.ANNULEE);
        commandeRepository.save(c);
    }
}
