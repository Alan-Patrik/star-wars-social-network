package com.starwars.resistence.modules.rebel.builder;

import com.starwars.resistence.modules.rebel.dto.RebelRequestDTO;
import com.starwars.resistence.modules.rebel.inventory.Inventory;
import lombok.Builder;

@Builder
public class RebelRequestDTOBuilder {
    @Builder.Default
    private int age = 20;
    @Builder.Default
    private String name = "Luke";
    @Builder.Default
    private String basename = "Brasil";
    @Builder.Default
    private Inventory inventory = InventoryBuilder.builder().build().buildInventory();
    @Builder.Default
    private String gender = "MALE";

    public RebelRequestDTO buildRebelRequestDTO() {
        return new RebelRequestDTO(age, name, basename, inventory, gender);
    }
}
