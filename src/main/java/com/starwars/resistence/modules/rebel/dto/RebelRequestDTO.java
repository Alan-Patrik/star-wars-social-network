package com.starwars.resistence.modules.rebel.dto;

import com.starwars.resistence.modules.rebel.inventory.Inventory;
import lombok.Data;

import java.io.Serializable;

@Data
public class RebelRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int age;
    private String name;
    private String basename;
    private Inventory inventory;
    private String gender;

    public RebelRequestDTO(int age, String name, String basename, Inventory inventory, String gender) {
        this.age = age;
        this.name = name;
        this.basename = basename;
        this.inventory = inventory;
        this.gender = gender;
    }
}
