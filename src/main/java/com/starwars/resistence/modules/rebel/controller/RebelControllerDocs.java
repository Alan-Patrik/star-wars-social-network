package com.starwars.resistence.modules.rebel.controller;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.http.HttpResponseDTO;
import com.starwars.resistence.modules.rebel.dto.RebelRequestDTO;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import com.starwars.resistence.modules.rebel.dto.RebelUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "Rebels Management")
public interface RebelControllerDocs {

    @Operation(summary = "Operação de criar um rebelde")
    ResponseEntity<HttpResponseDTO<RebelResponseDTO>> save(
            RebelRequestDTO rebelRequestDTO
    ) throws CustomNotFoundException, CustomBadRequestException, CustomInternalServerException;

    @Operation(summary = "Operaçao de atualizar a localizaçao de um rebelde")
    ResponseEntity<HttpResponseDTO<RebelResponseDTO>> update(
            @Parameter(description = "Id do rebelde que deseja alterar")
                    Long id,
            RebelUpdateRequestDTO rebelRequestDTO
    ) throws CustomNotFoundException, CustomBadRequestException;

    @Operation(summary = "Operaçao de listar de forma paginada todos os rebeldes")
    ResponseEntity<Page<RebelResponseDTO>> findAll(Pageable pageable);
}
