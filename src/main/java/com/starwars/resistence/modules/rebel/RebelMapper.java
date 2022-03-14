package com.starwars.resistence.modules.rebel;

import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RebelMapper {
    RebelMapper INSTANCE = Mappers.getMapper(RebelMapper.class);

    default RebelResponseDTO toDTO(Rebel model) {
        RebelResponseDTO rebelResponseDTO = new RebelResponseDTO();

        rebelResponseDTO.setId(model.getId());
        rebelResponseDTO.setName(model.getName());
        rebelResponseDTO.setAge(model.getAge());
        rebelResponseDTO.setGender(model.getGender());
        rebelResponseDTO.setInventory(model.getInventory());
        rebelResponseDTO.setLocalization(model.getLocalization());

        return rebelResponseDTO;
    }

    default Rebel toModel(RebelResponseDTO responseDTO) {
        Rebel rebel = new Rebel();

        rebel.setId(responseDTO.getId());
        rebel.setName(responseDTO.getName());
        rebel.setAge(responseDTO.getAge());
        rebel.setGender(responseDTO.getGender());
        rebel.setInventory(responseDTO.getInventory());
        rebel.setLocalization(responseDTO.getLocalization());

        return rebel;
    }
}
