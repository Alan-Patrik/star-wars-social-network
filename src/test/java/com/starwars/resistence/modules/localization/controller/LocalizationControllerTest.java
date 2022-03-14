package com.starwars.resistence.modules.localization.controller;

import com.starwars.resistence.modules.localization.LocalizationServiceImpl;
import com.starwars.resistence.modules.localization.builder.LocalizationRequestDTOBuilder;
import com.starwars.resistence.modules.localization.builder.LocalizationResponseDTOBuilder;
import com.starwars.resistence.modules.localization.dto.LocalizationRequestDTO;
import com.starwars.resistence.modules.localization.dto.LocalizationResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.starwars.resistence.utils.JsonConvertUtils.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockitoSettings
class LocalizationControllerTest {

    private static DateFormat dateFormat;
    private static LocalizationResponseDTOBuilder responseDTOBuilder;
    private static LocalizationRequestDTOBuilder requestDTOBuilder;
    private MockMvc mvc;
    @Mock
    private LocalizationServiceImpl service;
    @InjectMocks
    private LocalizationController controller;

    @BeforeEach
    void setUp() {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        responseDTOBuilder = LocalizationResponseDTOBuilder.builder().build();
        requestDTOBuilder = LocalizationRequestDTOBuilder.builder().build();
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    @DisplayName("When post is called then created status should be returned.")
    public void add() throws Exception {
        LocalizationRequestDTO requestDTO = requestDTOBuilder.buildLocalizationRequestDTO();
        LocalizationResponseDTO responseDTO = responseDTOBuilder.buildLocalizationResponseDTO();

        when(service.add(requestDTO)).thenReturn(responseDTO);

        mvc
                .perform(
                        post("/localization")
                                .contentType(APPLICATION_JSON)
                                .content(asJsonString(responseDTO))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.timestamp").value(dateFormat.format(System.currentTimeMillis())))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(CREATED.value()))
                .andExpect(jsonPath("$.data").value(responseDTO));
    }

    @Test
    @DisplayName("When post is called with invalid fields then bad request status should be returned.")
    public void addLocalizationWithInvalidRequestDTO() throws Exception {
        mvc.perform(post("/localization").contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}