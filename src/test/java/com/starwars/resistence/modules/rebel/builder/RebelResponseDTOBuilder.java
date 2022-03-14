package com.starwars.resistence.modules.rebel.builder;

import com.starwars.resistence.enums.GenderType;
import com.starwars.resistence.modules.localization.Localization;
import com.starwars.resistence.modules.localization.builder.LocalizationBuilder;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import com.starwars.resistence.modules.rebel.inventory.Inventory;
import lombok.Builder;

@Builder
public class RebelResponseDTOBuilder {
    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private int age = 20;
    @Builder.Default
    private String name = "Luke";
    @Builder.Default
    private GenderType gender = GenderType.MALE;
    @Builder.Default
    private Inventory inventory = InventoryBuilder.builder().build().buildInventory();
    @Builder.Default
    private Localization localization = LocalizationBuilder.builder().build().buildLocalization();

    public RebelResponseDTO buildResponseDTO() {
        return new RebelResponseDTO(id, name, age, gender, inventory, localization);
    }
}
