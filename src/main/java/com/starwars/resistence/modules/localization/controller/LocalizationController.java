package com.starwars.resistence.modules.localization.controller;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.http.HttpResponseDTO;
import com.starwars.resistence.modules.localization.LocalizationServiceImpl;
import com.starwars.resistence.modules.localization.dto.LocalizationRequestDTO;
import com.starwars.resistence.modules.localization.dto.LocalizationResponseDTO;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/localization")
@RequiredArgsConstructor
public class LocalizationController implements LocalizationControllerDocs {
    private final LocalizationServiceImpl localizationService;

    @PostMapping
    public ResponseEntity<HttpResponseDTO<LocalizationResponseDTO>> save(
            @RequestBody LocalizationRequestDTO localizationRequestDTO
    ) throws CustomBadRequestException, CustomInternalServerException {
        LocalizationResponseDTO response = localizationService.add(localizationRequestDTO);

        return ResponseEntity
                .status(CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(CREATED, response));
    }

    @GetMapping("/search")
    public ResponseEntity<HttpResponseDTO<Page<RebelResponseDTO>>> pageableLocalizationRebels(
            @RequestParam String basename, Pageable pageable
    ) throws CustomNotFoundException {
        Page<RebelResponseDTO> response = localizationService.getRebelsPerLocalization(basename, pageable);

        return ResponseEntity
                .status(OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(response));
    }
}
