package com.starwars.resistence.modules.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(rebel_reported_id) FROM REPORTS WHERE rebel_reported_id = :id")
    long amountReports(@Param("id") Long id);

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(REBEL_REPORTED_ID) FROM REPORTS WHERE REBEL_ACCUSER_ID = :rebelAccuserId AND " +
                    "REBEL_REPORTED_ID = :rebelReportedId HAVING COUNT(REBEL_REPORTED_ID) > 0"
    )
    Long amountAccuserReports(
            @Param("rebelAccuserId") Long rebelAccuserId, @Param("rebelReportedId") Long rebelReportedId
    );

    @Query(nativeQuery = true, value = "SELECT COUNT(id) FROM REBELS WHERE traitor = true")
    long amountTraitors();

    @Query(nativeQuery = true, value = "SELECT COUNT(id) FROM REBELS WHERE traitor = false")
    long amountRebels();
}
