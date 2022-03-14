package com.starwars.resistence.modules.report.controller;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.http.HttpResponseDTO;
import com.starwars.resistence.modules.report.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Report")
public interface ReportControllerDocs {

    @Operation(summary = "Operaçao de reportar um rebelde")
    ResponseEntity<HttpResponseDTO<ReportResponseDTO>> report(
            @Parameter(description = "Id do rebelde que vai ser reportado")
            @RequestParam Long rebelReportedId,
            @Parameter(description = "Id do rebelde que vai acusar")
            @RequestParam Long rebelAccuserId
    ) throws CustomNotFoundException, CustomBadRequestException;

    @Operation(summary = "Operaçao para exibir relatorio com a porcentagem de rebeldes/traidores")
    ResponseEntity<HttpResponseDTO<ReportDTO<ReportRebelDTO>>> reportPercentage(
            @Parameter(description = "Nome do grupo que deseja exibir relatorio", example = "rebels")
            @RequestParam String status
    ) throws CustomBadRequestException;

    @Operation(summary = "Operaçao para exibir relatorio de quantidade media de cada tipo de recurso por rebelde")
    ResponseEntity<HttpResponseDTO<ReportAverageDTO>> reportAverage() throws CustomInternalServerException;

    @Operation(summary = "Operaçao para exibir relatorio de pontos perdidos devido a traidores")
    ResponseEntity<HttpResponseDTO<ReportTraitorsDTO>> reportPointsLost() throws CustomInternalServerException;
}
