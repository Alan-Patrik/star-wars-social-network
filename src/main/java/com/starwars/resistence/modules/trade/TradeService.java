package com.starwars.resistence.modules.trade;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.exceptions.CustomTradeCanceledException;
import com.starwars.resistence.modules.rebel.inventory.Item;
import com.starwars.resistence.modules.trade.dto.TradeResponseDTO;

import java.util.List;

public interface TradeService {
    boolean calculateAmountPoints(List<Item> providerItems, List<Item> receptorItems);

    List<Item> validateIfItemsExist(List<Item> inventory, List<Item> offer) throws CustomTradeCanceledException, CustomInternalServerException;

    List<Item> removeItemsInventory(List<Item> inventory, List<Item> offer);

    List<Item> addItemsInventory(List<Item> inventory, List<Item> offer);

    List<TradeResponseDTO> makeNegotiation(Trade provider, Trade receptor) throws CustomNotFoundException, CustomTradeCanceledException, CustomBadRequestException, CustomInternalServerException;
}
