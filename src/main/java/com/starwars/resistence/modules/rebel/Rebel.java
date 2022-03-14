package com.starwars.resistence.modules.rebel;

import com.starwars.resistence.enums.GenderType;
import com.starwars.resistence.modules.localization.Localization;
import com.starwars.resistence.modules.rebel.inventory.Inventory;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Rebels")
public class Rebel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private int age;

    @Enumerated(STRING)
    private GenderType gender;

    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @ManyToOne(fetch = EAGER, cascade = ALL, optional = false)
    @JoinColumn(name = "localization_id", nullable = false, referencedColumnName = "id")
    private Localization localization;
    private boolean traitor;
}
