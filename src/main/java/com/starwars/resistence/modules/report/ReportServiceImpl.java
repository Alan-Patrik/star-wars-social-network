package com.starwars.resistence.modules.report;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.modules.rebel.Rebel;
import com.starwars.resistence.modules.rebel.RebelRepository;
import com.starwars.resistence.modules.report.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private static final ReportMapper reportMapper = ReportMapper.INSTANCE;
    private final ReportRepository reportRepository;
    private final RebelRepository rebelRepository;

    @Override
    public ReportResponseDTO reportRebel(
            Long rebelReportedId, Long rebelAccuserId
    ) throws CustomNotFoundException, CustomBadRequestException {
        Rebel rebelReported = rebelRepository
                .findById(rebelReportedId)
                .orElseThrow(() -> new CustomNotFoundException("Rebel nao existe"));

        Rebel rebelAccuser = rebelRepository
                .findById(rebelAccuserId)
                .orElseThrow(() -> new CustomNotFoundException("Rebel nao existe"));

        if (
                reportRepository.amountAccuserReports(rebelAccuser.getId(), rebelReported.getId()) != null
                        && reportRepository.amountAccuserReports(rebelAccuser.getId(), rebelReported.getId()) >= 1
        ) {
            throw new CustomBadRequestException(
                    String.format("Voce ja denunciou esse/a rebelde %s", rebelReported.getName())
            );
        }

        if (rebelAccuser.isTraitor()) {
            throw new CustomBadRequestException("Voce nao pode reportar, voce foi pro lado negro da força.");
        }

        if (reportRepository.amountReports(rebelReportedId) < 2) {
            Report report = new Report(
                    null,
                    Instant.now(),
                    Instant.now(),
                    rebelReported,
                    rebelAccuser
            );

            reportRepository.save(report);
            return reportMapper.successfullyReported(report, rebelReported, rebelAccuser);
        }

        Report report = new Report(
                null,
                Instant.now(),
                Instant.now(),
                rebelReported,
                rebelAccuser
        );

        rebelReported.setTraitor(true);
        rebelRepository.save(rebelReported);
        reportRepository.save(report);

        return reportMapper.traitorFound(report, rebelReported, rebelAccuser);
    }

    @Override
    public ReportDTO<ReportRebelDTO> calculatePercentage(String status) throws CustomBadRequestException {
        switch (status) {
            case "rebels":
                return calculatePercentageRebels();
            case "traitors":
                return calculatePercentageTraitors();
            default:
                throw new CustomBadRequestException("Por favor, insira um parametro valido");
        }
    }

    private ReportDTO<ReportRebelDTO> calculatePercentageTraitors() {
        double traitors = reportRepository.amountTraitors();
        double allRebels = rebelRepository.count();

        double total = (traitors / allRebels) * 100;
        List<ReportRebelDTO> allTraitors = rebelRepository
                .findByTraitorTrue()
                .stream()
                .map(reportMapper::toReportDTO)
                .collect(Collectors.toList());

        return new ReportDTO(
                "Relatorio de traidores",
                Instant.now(),
                String.format("%.0f%%", total),
                allTraitors
        );
    }

    private ReportDTO<ReportRebelDTO> calculatePercentageRebels() {
        double rebels = reportRepository.amountRebels();
        double allRebels = rebelRepository.count();

        double total = (rebels / allRebels) * 100;

        List<ReportRebelDTO> allTraitors = rebelRepository
                .findByTraitorFalse()
                .stream()
                .map(reportMapper::toReportDTO)
                .collect(Collectors.toList());

        return new ReportDTO(
                "Relatorio de rebeldes",
                Instant.now(),
                String.format("%.0f%%", total),
                allTraitors
        );
    }

    @Override
    public ReportAverageDTO calculateAverageResourcePerRebel() throws CustomInternalServerException {
        List<Rebel> rebels = rebelRepository.findByTraitorFalse();
        int rebelWeapons = 0;
        int rebelFood = 0;
        int rebelAmmunition = 0;
        int rebelWater = 0;

        for (Rebel rebel : rebels) {
            for (int i = 0; i < rebel.getInventory().getItems().size(); i++) {
                switch (rebel.getInventory().getItems().get(i).getName()) {
                    case "weapons":
                        rebelWeapons += rebel.getInventory().getItems().get(i).getQuantity();
                        continue;
                    case "food":
                        rebelFood += rebel.getInventory().getItems().get(i).getQuantity();
                        continue;
                    case "ammunition":
                        rebelAmmunition += rebel.getInventory().getItems().get(i).getQuantity();
                        continue;
                    case "water":
                        rebelWater += rebel.getInventory().getItems().get(i).getQuantity();
                        continue;
                    default:
                        throw new CustomInternalServerException("Não conseguimos processar sua requisição");
                }
            }
        }

        return new ReportAverageDTO(
                "Media de recursos",
                String.format("%s per Rebel", (rebelWeapons / rebels.size())),
                String.format("%s per Rebel", (rebelAmmunition / rebels.size())),
                String.format("%s per Rebel", (rebelFood / rebels.size())),
                String.format("%s per Rebel", (rebelWater / rebels.size()))
        );
    }

    @Override
    public ReportTraitorsDTO calculateAmountPointsLost() throws CustomInternalServerException {
        List<Rebel> traitors = rebelRepository.findByTraitorTrue();
        int traitorWeapons = 0;
        int traitorFood = 0;
        int traitorAmmunition = 0;
        int traitorWater = 0;

        for (Rebel rebel : traitors) {
            for (int i = 0; i < rebel.getInventory().getItems().size(); i++) {
                switch (rebel.getInventory().getItems().get(i).getName()) {
                    case "weapons":
                        traitorWeapons += (
                                rebel.getInventory().getItems().get(i).getValue()
                                        * rebel.getInventory().getItems().get(i).getQuantity()
                        );
                        continue;
                    case "food":
                        traitorFood += (
                                rebel.getInventory().getItems().get(i).getValue()
                                        * rebel.getInventory().getItems().get(i).getQuantity()
                        );
                        continue;
                    case "ammunition":
                        traitorAmmunition += (
                                rebel.getInventory().getItems().get(i).getValue()
                                        * rebel.getInventory().getItems().get(i).getQuantity()
                        );
                        continue;
                    case "water":
                        traitorWater += (
                                rebel.getInventory().getItems().get(i).getValue()
                                        * rebel.getInventory().getItems().get(i).getQuantity()
                        );
                        continue;
                    default:
                        throw new CustomInternalServerException("Não conseguimos processar sua requisição");
                }
            }
        }

        int total = traitorWeapons + traitorWater + traitorFood + traitorAmmunition;
        return new ReportTraitorsDTO(
                "Pontos perdidos devido a traidores",
                traitors.size(),
                total
        );
    }
}
