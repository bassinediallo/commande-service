package com.groupeisi.commandeservice.mapper;
import com.groupeisi.commandeservice.dto.CommandeDTO;
import com.groupeisi.commandeservice.entities.Commande;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface CommandeMapper {
    CommandeDTO toDTO(Commande c);
    Commande toEntity(CommandeDTO dto);
}
