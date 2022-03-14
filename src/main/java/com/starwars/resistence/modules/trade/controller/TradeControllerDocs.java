package com.starwars.resistence.modules.trade.controller;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.exceptions.CustomTradeCanceledException;
import com.starwars.resistence.modules.trade.Trade;
import com.starwars.resistence.modules.trade.dto.TradeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Trading")
public interface TradeControllerDocs {

    @Operation(summary = "Operaçao para negociar items entre rebeldes")
    ResponseEntity<List<TradeResponseDTO>> negotiation(
            @RequestBody Trade[] trades
    ) throws CustomTradeCanceledException, CustomNotFoundException, CustomBadRequestException,
            CustomInternalServerException;
}
