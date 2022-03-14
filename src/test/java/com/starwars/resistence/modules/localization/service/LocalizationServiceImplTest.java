package com.starwars.resistence.modules.localization.service;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.modules.localization.Localization;
import com.starwars.resistence.modules.localization.LocalizationMapper;
import com.starwars.resistence.modules.localization.LocalizationRepository;
import com.starwars.resistence.modules.localization.LocalizationServiceImpl;
import com.starwars.resistence.modules.localization.builder.LocalizationBuilder;
import com.starwars.resistence.modules.localization.dto.LocalizationResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
class LocalizationServiceImplTest {

    private static final LocalizationMapper mapper = LocalizationMapper.INSTANCE;
    private LocalizationBuilder localizationBuilder;
    @InjectMocks
    private LocalizationServiceImpl service;
    @Mock
    private LocalizationRepository localizationPersistence;

    @BeforeEach
    void setUp() {
        localizationBuilder = LocalizationBuilder.builder().build();
    }

    @Test
    @DisplayName("Should save a localization in the database")
    void add() throws CustomInternalServerException, CustomBadRequestException {
        Localization localization = localizationBuilder.buildLocalization();
        when(localizationPersistence.save(any(Localization.class))).thenReturn(localization);
        var test = mapper.toRequestDTO(localization);
        LocalizationResponseDTO test2 = service.add(test);
        Assertions.assertNotNull(test);
        Assertions.assertEquals(test, mapper.fromLocalizationResponseDTOToLocalizationRequestDTO(test2));
    }
}
