package com.starwars.resistence.modules.rebel;

import com.starwars.resistence.enums.GenderType;
import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.modules.localization.Localization;
import com.starwars.resistence.modules.localization.LocalizationRepository;
import com.starwars.resistence.modules.rebel.dto.RebelRequestDTO;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import com.starwars.resistence.modules.rebel.dto.RebelUpdateRequestDTO;
import com.starwars.resistence.modules.rebel.inventory.Inventory;
import com.starwars.resistence.modules.rebel.inventory.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RebelServiceImpl implements RebelService {
    private static final RebelMapper mapper = RebelMapper.INSTANCE;
    private final RebelRepository rebelRepository;
    private final LocalizationRepository localizationRepository;

    @Override
    public Rebel verifyIfRebelExists(Long id) throws CustomNotFoundException {
        return rebelRepository
                .findById(id)
                .orElseThrow(() -> new CustomNotFoundException(
                        String.format("Rebelde com o id %s não foi encontrado.", id)
                ));
    }

    @Override
    public RebelResponseDTO add(RebelRequestDTO rebelRequestDTO) throws CustomNotFoundException, CustomBadRequestException, CustomInternalServerException {
        Localization localization = localizationRepository
                .findByBasename(rebelRequestDTO.getBasename())
                .orElseThrow(() -> new CustomNotFoundException("A base inserida não existe."));

        List<Item> items = rebelRequestDTO.getInventory().getItems();

        for (int i = 0; i < items.size(); i++) {
            items.get(i).populatingValueField();
        }

        Inventory inventory = rebelRequestDTO.getInventory();
        inventory.setItems(items);

        Rebel rebel = new Rebel(
                null,
                rebelRequestDTO.getName(),
                rebelRequestDTO.getAge(),
                GenderType.valueOf(rebelRequestDTO.getGender()),
                inventory,
                localization,
                false
        );

        rebelRepository.save(rebel);
        return mapper.toDTO(rebel);
    }

    @Override
    public RebelResponseDTO update(Long id, RebelUpdateRequestDTO requestDTO) throws CustomNotFoundException, CustomBadRequestException {
        Rebel rebel = verifyIfRebelExists(id);
        Localization localization = localizationRepository
                .findByBasename(requestDTO.getBasename())
                .orElseThrow(() -> new CustomNotFoundException("Base inserida nao existe"));

        if (localization != null) {
            rebel.setLocalization(localization);
            rebelRepository.save(rebel);

            return mapper.toDTO(rebel);
        }

        throw new CustomBadRequestException("Essa localizaçao nao existe");
    }

    @Override
    public Page<RebelResponseDTO> findAll(Pageable pageable) {
        return rebelRepository.findAll(pageable).map(mapper::toDTO);
    }
}
