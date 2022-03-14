package com.starwars.resistence.modules.localization.controller;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.http.HttpResponseDTO;
import com.starwars.resistence.modules.localization.dto.LocalizationRequestDTO;
import com.starwars.resistence.modules.localization.dto.LocalizationResponseDTO;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Localization")
public interface LocalizationControllerDocs {

    @Operation(summary = "Operaçao de criar uma localizaçao")
    ResponseEntity<HttpResponseDTO<LocalizationResponseDTO>> save(
            @RequestBody LocalizationRequestDTO localizationRequestDTO
    ) throws CustomBadRequestException, CustomInternalServerException;

    @Operation(summary = "Operaçao de buscar todos os rebeldes em uma localizaçao")
    ResponseEntity<HttpResponseDTO<Page<RebelResponseDTO>>> pageableLocalizationRebels(
            @Parameter(description = "Nome da base que deseja procurar")
            @RequestParam String basename,
            Pageable pageable
    ) throws CustomNotFoundException;
}
