package com.starwars.resistence.modules.report;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.modules.rebel.Rebel;
import com.starwars.resistence.modules.report.dto.ReportRebelDTO;
import com.starwars.resistence.modules.report.dto.ReportResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.Instant;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    default ReportResponseDTO successfullyReported(Report model, Rebel rebelReported, Rebel rebelAccuser) throws CustomBadRequestException {
        if (model == null)
            throw new CustomBadRequestException("Não foi possível converter null para ReportResponseDTO");

        ReportRebelDTO reportedRebelDTO = new ReportRebelDTO(
                rebelReported.getId(),
                rebelReported.getName(),
                rebelReported.getGender(),
                rebelReported.getAge(),
                rebelReported.getLocalization().getBasename()
        );

        ReportRebelDTO accuserRebelDTO = new ReportRebelDTO(
                rebelAccuser.getId(),
                rebelAccuser.getName(),
                rebelAccuser.getGender(),
                rebelAccuser.getAge(),
                rebelAccuser.getLocalization().getBasename()
        );

        return new ReportResponseDTO(
                model.getId(),
                Instant.now(),
                Instant.now(),
                "Foi reportado com sucesso",
                reportedRebelDTO,
                accuserRebelDTO
        );
    }

    default ReportResponseDTO traitorFound(Report model, Rebel rebelReported, Rebel rebelAccuser) throws CustomBadRequestException {
        if (model == null)
            throw new CustomBadRequestException("Não foi possível converter null para ReportResponseDTO");

        ReportRebelDTO reportedRebelDTO = new ReportRebelDTO(
                rebelReported.getId(),
                rebelReported.getName(),
                rebelReported.getGender(),
                rebelReported.getAge(),
                rebelReported.getLocalization().getBasename()
        );

        ReportRebelDTO accuserRebelDTO = new ReportRebelDTO(
                rebelAccuser.getId(),
                rebelAccuser.getName(),
                rebelAccuser.getGender(),
                rebelAccuser.getAge(),
                rebelAccuser.getLocalization().getBasename()
        );

        return new ReportResponseDTO(
                model.getId(),
                Instant.now(),
                Instant.now(),
                "Descobrimos um traidor. Obrigado pelo seu reporte!",
                reportedRebelDTO,
                accuserRebelDTO
        );
    }

    default ReportRebelDTO toReportDTO(Rebel model) {
        return new ReportRebelDTO(
                model.getId(),
                model.getName(),
                model.getGender(),
                model.getAge(),
                model.getLocalization().getBasename()
        );
    }
}
