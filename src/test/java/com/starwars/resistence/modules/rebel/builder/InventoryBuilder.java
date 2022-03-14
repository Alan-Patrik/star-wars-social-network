package com.starwars.resistence.modules.rebel.builder;

import com.starwars.resistence.modules.rebel.inventory.Inventory;
import com.starwars.resistence.modules.rebel.inventory.Item;
import lombok.Builder;

import java.util.List;

@Builder
public class InventoryBuilder {
    @Builder.Default
    private Long id = 1L;
    private List<Item> items;

    public Inventory buildInventory() {
        Item item1 = new Item(1L, "weapons", 10, 4);
        Item item2 = new Item(2L, "ammunition", 10, 3);
        Item item3 = new Item(3L, "water", 10, 2);
        Item item4 = new Item(4L, "food", 10, 1);

        return new Inventory(id, List.of(item1, item2, item3, item4));
    }
}
