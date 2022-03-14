package com.starwars.resistence.modules.rebel.dto;

import com.starwars.resistence.enums.GenderType;
import com.starwars.resistence.modules.localization.Localization;
import com.starwars.resistence.modules.rebel.inventory.Inventory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RebelResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int age;
    private String name;
    private GenderType gender;
    private Inventory inventory;
    private Localization localization;

    public RebelResponseDTO(
            Long id, String name, int age, GenderType gender, Inventory inventory, Localization localization
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.inventory = inventory;
        this.localization = localization;
    }
}