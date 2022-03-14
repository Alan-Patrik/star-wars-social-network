package com.starwars.resistence.modules.trade.dto;

import com.starwars.resistence.modules.rebel.inventory.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class TradeResponseDTO implements Serializable {
    private String name;
    private List<Item> exchangedItems;
}
