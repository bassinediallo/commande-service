package com.groupeisi.commandeservice.controller;
import com.groupeisi.commandeservice.dto.CommandeDTO;
import com.groupeisi.commandeservice.service.CommandeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/commandes") @RequiredArgsConstructor
@Tag(name = "Commandes") @SecurityRequirement(name = "bearerAuth")
public class CommandeController {
    private final CommandeService commandeService;
    @GetMapping @Operation(summary = "Toutes les commandes") public ResponseEntity<List<CommandeDTO>> findAll() { return ResponseEntity.ok(commandeService.findAll()); }
    @GetMapping("/{id}") @Operation(summary = "Commande par ID") public ResponseEntity<CommandeDTO> findById(@PathVariable Long id) { return ResponseEntity.ok(commandeService.findById(id)); }
    @GetMapping("/etudiant/{etudiantId}") @Operation(summary = "Commandes d'un étudiant") public ResponseEntity<List<CommandeDTO>> findByEtudiant(@PathVariable Long etudiantId) { return ResponseEntity.ok(commandeService.findByEtudiant(etudiantId)); }
    @PostMapping @Operation(summary = "Passer une commande (appel sync → produit-service + Kafka → paiement-service)")
    public ResponseEntity<CommandeDTO> passerCommande(@RequestBody CommandeDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(commandeService.passerCommande(dto)); }
    @PutMapping("/{id}/annuler") @Operation(summary = "Annuler une commande") public ResponseEntity<Void> annuler(@PathVariable Long id) { commandeService.annuler(id); return ResponseEntity.noContent().build(); }
}
