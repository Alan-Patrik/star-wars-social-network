package com.starwars.resistence.modules.localization;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.modules.localization.dto.LocalizationRequestDTO;
import com.starwars.resistence.modules.localization.dto.LocalizationResponseDTO;
import com.starwars.resistence.modules.rebel.Rebel;
import com.starwars.resistence.modules.rebel.RebelMapper;
import com.starwars.resistence.modules.rebel.RebelRepository;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {
    private static final LocalizationMapper mapper = LocalizationMapper.INSTANCE;
    private static final RebelMapper rebelMapper = RebelMapper.INSTANCE;
    private final LocalizationRepository localizationRepository;
    private final RebelRepository rebelRepository;


    @Override
    public void verifyIfLocalizationExists(String name) throws CustomBadRequestException {
        Optional<Localization> localization = localizationRepository.findByBasename(name);

        if (localization.isPresent())
            throw new CustomBadRequestException("Essa localizaçao ja existe");
    }

    @Override
    public LocalizationResponseDTO add(LocalizationRequestDTO localizationRequestDTO) throws CustomBadRequestException, CustomInternalServerException {
        verifyIfLocalizationExists(localizationRequestDTO.getBasename());
        Localization localization = new Localization();

        localization.setBasename(localizationRequestDTO.getBasename());
        localization.setLatitude(localizationRequestDTO.getLatitude());
        localization.setLongitude(localizationRequestDTO.getLongitude());
        localizationRepository.save(localization);

        return mapper.toDTO(localization);
    }

    @Override
    public Page<RebelResponseDTO> getRebelsPerLocalization(String basename, Pageable pageable) throws CustomNotFoundException {
        Localization localization = localizationRepository
                .findByBasename(basename)
                .orElseThrow(() -> new CustomNotFoundException("A base nao foi encontrada"));

        Page<Rebel> rebelsPerLocalization = rebelRepository
                .findByLocalization(localization.getId(), pageable);

        return rebelsPerLocalization.map(rebelMapper::toDTO);
    }

}
