package com.starwars.resistence.modules.localization;

import com.starwars.resistence.modules.localization.dto.LocalizationRequestDTO;
import com.starwars.resistence.modules.localization.dto.LocalizationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocalizationMapper {
    LocalizationMapper INSTANCE = Mappers.getMapper(LocalizationMapper.class);

    default LocalizationResponseDTO toDTO(Localization model) {
        LocalizationResponseDTO response = new LocalizationResponseDTO();

        response.setId(model.getId());
        response.setBasename(model.getBasename());
        response.setLatitude(model.getLatitude());
        response.setLongitude(model.getLongitude());

        return response;
    }

    default LocalizationRequestDTO toRequestDTO(Localization localization) {
        LocalizationRequestDTO response = new LocalizationRequestDTO();

        response.setBasename(localization.getBasename());
        response.setLatitude(localization.getLatitude());
        response.setLongitude(localization.getLongitude());

        return response;
    }

    default LocalizationRequestDTO fromLocalizationResponseDTOToLocalizationRequestDTO(
            LocalizationResponseDTO localizationResponseDTO) {
        LocalizationRequestDTO response = new LocalizationRequestDTO();

        response.setBasename(localizationResponseDTO.getBasename());
        response.setLatitude(localizationResponseDTO.getLatitude());
        response.setLongitude(localizationResponseDTO.getLongitude());

        return response;
    }
}
