package com.example.infoevenement.mapper;

import com.example.infoevenement.dao.Evenement;
import com.example.infoevenement.dto.EvenementDto;
import com.example.infoevenement.dto.EvenementInput;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EvenementMapper {
        EvenementDto toDto(Evenement evenement);
        Evenement toEntity(EvenementInput evenementInput);
}


