package com.starwars.resistence.modules.report.controller;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.http.HttpResponseDTO;
import com.starwars.resistence.modules.report.ReportServiceImpl;
import com.starwars.resistence.modules.report.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController implements ReportControllerDocs{
    private final ReportServiceImpl service;

    @PostMapping
    public ResponseEntity<HttpResponseDTO<ReportResponseDTO>> report(
            @RequestParam Long rebelReportedId,
            @RequestParam Long rebelAccuserId
    ) throws CustomNotFoundException, CustomBadRequestException {
        ReportResponseDTO response = service.reportRebel(rebelReportedId, rebelAccuserId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(CREATED, response));
    }

    @GetMapping("/percentage")
    public ResponseEntity<HttpResponseDTO<ReportDTO>> reportPercentage(
            @RequestParam String status
    ) throws CustomBadRequestException {
        ReportDTO response = service.calculatePercentage(status);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(response));
    }

    @GetMapping("/average")
    public ResponseEntity<HttpResponseDTO<ReportAverageDTO>> reportAverage() throws CustomInternalServerException {
        ReportAverageDTO response = service.calculateAverageResourcePerRebel();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(response));
    }

    @GetMapping("/points")
    public ResponseEntity<HttpResponseDTO<ReportTraitorsDTO>> reportPointsLost() throws CustomInternalServerException {
        ReportTraitorsDTO response = service.calculateAmountPointsLost();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(response));
    }
}
