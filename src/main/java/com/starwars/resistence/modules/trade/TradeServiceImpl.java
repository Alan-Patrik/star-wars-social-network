package com.starwars.resistence.modules.trade;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.exceptions.CustomTradeCanceledException;
import com.starwars.resistence.modules.rebel.Rebel;
import com.starwars.resistence.modules.rebel.RebelRepository;
import com.starwars.resistence.modules.rebel.inventory.Item;
import com.starwars.resistence.modules.trade.dto.TradeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    private static final TradeMapper mapper = TradeMapper.INSTANCE;
    private final RebelRepository rebelRepository;

    @Override
    public boolean calculateAmountPoints(List<Item> providerItems, List<Item> receptorItems) {
        int receptorPoints = 0;
        int providerPoints = 0;

        for (Item item : receptorItems) {
            receptorPoints = receptorPoints + (item.getValue() * item.getQuantity());
        }

        for (Item item : providerItems) {
            providerPoints = providerPoints + (item.getValue() * item.getQuantity());
        }

        return providerPoints == receptorPoints;
    }

    @Override
    public List<Item> validateIfItemsExist(List<Item> inventory, List<Item> offer) throws CustomTradeCanceledException, CustomInternalServerException {
        List<Item> validItems = new ArrayList<>();

        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < offer.size(); j++) {
                String nameItem = inventory.get(i).getName();
                int quantityItem = inventory.get(i).getQuantity();

                if (offer.get(j).getName().equals(nameItem)) {
                    if (offer.get(j).getQuantity() > quantityItem) {
                        validItems = new ArrayList<>();
                    } else {
                        Item item = offer.get(j);
                        item.setId(inventory.get(i).getId());
                        item.populatingValueField();
                        validItems.add(item);
                    }
                } else {
                    validItems.add(null);
                }
            }
        }

        validItems.removeIf(Objects::isNull);

        if (inventory.size() < offer.size()) {
            throw new CustomTradeCanceledException(
                    "Voce nao possui a mesma quantidade de items que esta ofertando."
            );
        }

        return validItems;
    }

    @Override
    public List<Item> removeItemsInventory(List<Item> inventory, List<Item> offer) {
        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < offer.size(); j++) {
                if (inventory.get(i).getName().equals(offer.get(j).getName())) {
                    inventory.get(i).setQuantity(inventory.get(i).getQuantity() - offer.get(j).getQuantity());
                }
            }
        }

        return inventory;
    }

    @Override
    public List<Item> addItemsInventory(List<Item> inventory, List<Item> offer) {
        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < offer.size(); j++) {
                if (inventory.get(i).getName().equals(offer.get(j).getName())) {
                    inventory.get(i).setQuantity(inventory.get(i).getQuantity() + offer.get(j).getQuantity());
                }
            }
        }

        return inventory;
    }

    @Override
    public List<TradeResponseDTO> makeNegotiation(
            Trade provider, Trade receptor
    ) throws CustomNotFoundException, CustomTradeCanceledException, CustomBadRequestException, CustomInternalServerException {
        Rebel rebelProvider = rebelRepository.findById(provider.getRebelId())
                .orElseThrow(() -> new CustomNotFoundException("Esse rebelde nao existe"));

        Rebel rebelReceptor = rebelRepository.findById(receptor.getRebelId())
                .orElseThrow(() -> new CustomNotFoundException("Esse rebelde nao existe"));

        List<Item> providerTradeItems = validateIfItemsExist(
                rebelProvider.getInventory().getItems(), provider.getItems()
        );

        List<Item> receptorTradeItems = validateIfItemsExist(
                rebelReceptor.getInventory().getItems(), receptor.getItems()
        );

        if (rebelProvider.isTraitor() || rebelReceptor.isTraitor()) {
            throw new CustomBadRequestException("Um traidor nao pode negociar");
        } else {
            if ((providerTradeItems.isEmpty() || receptorTradeItems.isEmpty())) {
                throw new CustomTradeCanceledException("Voce nao pode efetuar a negociaçao sem items.");
            } else {
                if (!calculateAmountPoints(providerTradeItems, receptorTradeItems)) {
                    throw new CustomTradeCanceledException("Nao possuem o mesmo valor de troca");
                } else {
                    rebelProvider
                            .getInventory()
                            .setItems(addItemsInventory(rebelProvider.getInventory().getItems(), receptorTradeItems));

                    rebelProvider
                            .getInventory()
                            .setItems(removeItemsInventory(rebelProvider.getInventory().getItems(), providerTradeItems));

                    rebelReceptor
                            .getInventory()
                            .setItems(addItemsInventory(rebelReceptor.getInventory().getItems(), providerTradeItems));

                    rebelReceptor
                            .getInventory()
                            .setItems(removeItemsInventory(rebelReceptor.getInventory().getItems(), receptorTradeItems));

                    rebelRepository.saveAll(List.of(rebelProvider, rebelReceptor));
                }
            }
        }

        TradeResponseDTO providerResponse = new TradeResponseDTO(rebelProvider.getName(), providerTradeItems);
        TradeResponseDTO receptorResponse = new TradeResponseDTO(rebelReceptor.getName(), receptorTradeItems);

        return mapper.toListDTO(providerResponse, receptorResponse);
    }
}
