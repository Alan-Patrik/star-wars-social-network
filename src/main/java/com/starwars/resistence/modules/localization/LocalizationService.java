package com.starwars.resistence.modules.localization;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.modules.localization.dto.LocalizationRequestDTO;
import com.starwars.resistence.modules.localization.dto.LocalizationResponseDTO;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocalizationService {
    void verifyIfLocalizationExists(String name) throws CustomBadRequestException;

    LocalizationResponseDTO add(LocalizationRequestDTO localizationRequestDTO) throws CustomBadRequestException, CustomInternalServerException;

    Page<RebelResponseDTO> getRebelsPerLocalization(String basename, Pageable pageable) throws CustomNotFoundException;
}
