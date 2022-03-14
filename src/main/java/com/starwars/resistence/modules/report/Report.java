package com.starwars.resistence.modules.report;

import com.starwars.resistence.modules.rebel.Rebel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant createdAt;
    private Instant lastReportAt;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "rebel_reported_id")
    private Rebel rebelReported;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "rebel_accuser_id")
    private Rebel rebelAccuser;
}
