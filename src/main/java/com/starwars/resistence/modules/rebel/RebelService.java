package com.starwars.resistence.modules.rebel;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.modules.rebel.dto.RebelRequestDTO;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import com.starwars.resistence.modules.rebel.dto.RebelUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RebelService {
    Rebel verifyIfRebelExists(Long id) throws CustomNotFoundException;

    RebelResponseDTO add(RebelRequestDTO rebelRequestDTO) throws CustomNotFoundException, CustomBadRequestException, CustomInternalServerException;

    RebelResponseDTO update(Long id, RebelUpdateRequestDTO rebelUpdateRequestDTO) throws CustomNotFoundException, CustomBadRequestException;

    Page<RebelResponseDTO> findAll(Pageable pageable);
}
