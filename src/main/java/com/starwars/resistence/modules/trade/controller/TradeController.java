package com.starwars.resistence.modules.trade.controller;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.exceptions.CustomTradeCanceledException;
import com.starwars.resistence.modules.trade.Trade;
import com.starwars.resistence.modules.trade.TradeServiceImpl;
import com.starwars.resistence.modules.trade.dto.TradeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trading")
public class TradeController implements TradeControllerDocs{
    private final TradeServiceImpl service;

    @PostMapping
    public ResponseEntity<List<TradeResponseDTO>> negotiation(
            @RequestBody Trade[] trades
    ) throws CustomTradeCanceledException, CustomNotFoundException, CustomBadRequestException, CustomInternalServerException {
        List<TradeResponseDTO> response = service.makeNegotiation(trades[0], trades[1]);

        return ResponseEntity
                .status(OK)
                .contentType(APPLICATION_JSON)
                .body(response);
    }
}
