package com.starwars.resistence.modules.localization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starwars.resistence.modules.rebel.Rebel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Localizations")
public class Localization {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private double latitude;

    @Column(nullable = false, unique = true)
    private double longitude;

    @Column(nullable = false, unique = true)
    private String basename;

    @JsonIgnore
    @OneToMany(fetch = LAZY, mappedBy = "localization", orphanRemoval = true)
    private List<Rebel> rebels = new ArrayList<>();

    public Localization(double latitude, double longitude, String basename) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.basename = basename;
    }
}
