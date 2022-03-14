package com.starwars.resistence.modules.trade;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.modules.trade.dto.TradeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TradeMapper {
    TradeMapper INSTANCE = Mappers.getMapper(TradeMapper.class);

    default List<TradeResponseDTO> toListDTO(TradeResponseDTO provider, TradeResponseDTO receptor) throws CustomBadRequestException {
        List<TradeResponseDTO> response = new ArrayList<>();

        if (provider == null && receptor == null) {
            throw new CustomBadRequestException("Nao foi possivel converter null to TradeResponseDTO");
        }

        response.add(provider);
        response.add(receptor);

        return response;
    }
}
