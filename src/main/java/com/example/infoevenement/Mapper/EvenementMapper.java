package com.example.infoevenement.Mapper;

import com.example.infoevenement.dao.Evenement;
import com.example.infoevenement.dto.EvenementDto;
import com.example.infoevenement.dto.EvenementInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvenementMapper {

        @Mapping(source = "libelle", target = "libelle")
        @Mapping(source = "description", target = "description")
        @Mapping(source = "lieux", target = "lieux")
        EvenementDto toDto(Evenement evenement);

        @Mapping(source = "libelle", target = "libelle")
        @Mapping(source = "description", target = "description")
        @Mapping(source = "lieux", target = "lieux")
        @Mapping(target = "id", ignore = true)
        Evenement toEntity(EvenementInput evenementInput);
    }


