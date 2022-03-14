package com.starwars.resistence.modules.rebel.service;

import com.starwars.resistence.exceptions.CustomBadRequestException;
import com.starwars.resistence.exceptions.CustomInternalServerException;
import com.starwars.resistence.exceptions.CustomNotFoundException;
import com.starwars.resistence.modules.localization.Localization;
import com.starwars.resistence.modules.localization.LocalizationRepository;
import com.starwars.resistence.modules.localization.LocalizationServiceImpl;
import com.starwars.resistence.modules.localization.builder.LocalizationBuilder;
import com.starwars.resistence.modules.rebel.Rebel;
import com.starwars.resistence.modules.rebel.RebelMapper;
import com.starwars.resistence.modules.rebel.RebelRepository;
import com.starwars.resistence.modules.rebel.RebelServiceImpl;
import com.starwars.resistence.modules.rebel.builder.RebelRequestDTOBuilder;
import com.starwars.resistence.modules.rebel.builder.RebelResponseDTOBuilder;
import com.starwars.resistence.modules.rebel.dto.RebelRequestDTO;
import com.starwars.resistence.modules.rebel.dto.RebelResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
class RebelServiceImplTest {

    private static final RebelMapper rebelMapper = RebelMapper.INSTANCE;
    @Mock
    private RebelRepository rebelRepository;
    @Mock
    private LocalizationRepository localizationRepository;
    @InjectMocks
    private RebelServiceImpl rebelService;
    @InjectMocks
    private LocalizationServiceImpl localizationService;
    private RebelRequestDTOBuilder requestDTOBuilder;
    private RebelResponseDTOBuilder responseDTOBuilder;
    private LocalizationBuilder localizationBuilder;

    @BeforeEach
    void setUp() {
        requestDTOBuilder = RebelRequestDTOBuilder.builder().build();
        responseDTOBuilder = RebelResponseDTOBuilder.builder().build();

        localizationBuilder = LocalizationBuilder.builder().build();

        Localization localization = localizationBuilder.buildLocalization();
        localizationRepository.save(localization);
    }

    //    @Test
    void add() throws CustomNotFoundException, CustomBadRequestException, CustomInternalServerException, InterruptedException {
        RebelRequestDTO request = requestDTOBuilder.buildRebelRequestDTO();
        RebelResponseDTO response = responseDTOBuilder.buildResponseDTO();
        Rebel rebel = rebelMapper.toModel(response);

        Localization localization = LocalizationBuilder.builder().build().buildLocalization();
        when(localizationRepository.save(any(Localization.class))).thenReturn(localization);
        when(rebelRepository.save(rebel)).thenReturn(new Rebel());
        RebelResponseDTO createdRebel = rebelService.add(request);

        Assertions.assertEquals(createdRebel, response);
        assertThat(createdRebel, is(equalTo(response)));
    }
}