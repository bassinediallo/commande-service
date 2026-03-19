package com.groupeisi.commandeservice.kafka;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// Producteur Kafka — envoie la commande vers paiement-service
@Service @RequiredArgsConstructor
public class CommandeProducer {
    private static final Logger log = LoggerFactory.getLogger(CommandeProducer.class);
    private static final String TOPIC = "commande-topic";
    private final KafkaTemplate<String, CommandeEvent> kafkaTemplate;

    public void envoyerCommande(CommandeEvent event) {
        log.info("📤 Envoi Kafka → topic: {} | commandeId: {}", TOPIC, event.getCommandeId());
        kafkaTemplate.send(TOPIC, event);
        log.info("✅ Message Kafka envoyé avec succès pour commande: {}", event.getCommandeId());
    }
}
