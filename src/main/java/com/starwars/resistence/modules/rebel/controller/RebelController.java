package com.starwars.resistence.modules.rebel.controller;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.http.HttpResponseDTO;
import com.starwars.resistence.modules.rebel.RebelServiceImpl;
import com.starwars.resistence.modules.rebel.dto.RebelRequestDTO;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import com.starwars.resistence.modules.rebel.dto.RebelUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rebel")
public class RebelController implements RebelControllerDocs {
    private final RebelServiceImpl rebelService;

    @PostMapping
    public ResponseEntity<HttpResponseDTO<RebelResponseDTO>> save(
            @RequestBody RebelRequestDTO rebelRequestDTO
    ) throws CustomNotFoundException, CustomBadRequestException, CustomInternalServerException {
        RebelResponseDTO response = rebelService.add(rebelRequestDTO);

        return ResponseEntity
                .status(CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(CREATED, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponseDTO<RebelResponseDTO>> update(
            @PathVariable Long id, @RequestBody RebelUpdateRequestDTO rebelRequestDTO
    ) throws CustomNotFoundException, CustomBadRequestException {
        RebelResponseDTO response = rebelService.update(id, rebelRequestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(response));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<RebelResponseDTO>> findAll(Pageable pageable) {
        Page<RebelResponseDTO> response = rebelService.findAll(pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
