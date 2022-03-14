package com.starwars.resistence.modules.trade;

import com.starwars.resistence.modules.rebel.inventory.Item;
import lombok.Data;

import java.util.List;

@Data
public class Trade {
    private Long rebelId;
    private List<Item> items;
}
