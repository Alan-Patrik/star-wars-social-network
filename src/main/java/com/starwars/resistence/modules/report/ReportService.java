package com.starwars.resistence.modules.report;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.modules.report.dto.*;

public interface ReportService {
    ReportResponseDTO reportRebel(Long rebelReported, Long rebelAccuser) throws CustomNotFoundException, CustomBadRequestException;

    ReportDTO<ReportRebelDTO> calculatePercentage(String status) throws CustomBadRequestException;

    ReportAverageDTO calculateAverageResourcePerRebel() throws CustomInternalServerException;

    ReportTraitorsDTO calculateAmountPointsLost() throws CustomInternalServerException;
}
