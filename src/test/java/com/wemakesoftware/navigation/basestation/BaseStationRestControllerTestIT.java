package com.wemakesoftware.navigation.basestation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemakesoftware.navigation.NavigationController;
import com.wemakesoftware.navigation.mobilestation.MobileStationEntityRepository;
import com.wemakesoftware.navigation.mobilestation.MobileStationService;
import com.wemakesoftware.navigation.mobilestation.MobileStationServiceImpl;
import com.wemakesoftware.navigation.mobilestation.dto.HearBeatDto;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseStationRestControllerTestIT {

    List<BaseStationEntity> baseStationEntities = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;
    private MobileStationService mobileStationService;
    private BaseStationService baseStationService;
    private NavigationController navigationController;
    @Autowired
    private MobileStationEntityRepository mobileStationEntityRepository;
    @Autowired
    private BaseStationEntityRepository baseStationEntityRepository;
    @Autowired
    private BaseStationEntityMapper mapper;

    private void seed() throws IOException {

        for (float i = -5; i <= 5f; i++) {

            for (float j = -5; j <= 5f; j++) {

                BaseStationEntity baseStationEntity = new BaseStationEntity(
                        UUID.randomUUID().toString(),
                        3f,
                        i * 10,
                        j * 10
                );

                baseStationEntities.add(baseStationEntityRepository.save(baseStationEntity));

            }
        }

    }

    @BeforeEach
    public void setup() throws IOException {

        mobileStationService = new MobileStationServiceImpl(mobileStationEntityRepository, baseStationEntityRepository);

        baseStationService = new BaseStationServiceImpl(baseStationEntityRepository, mapper);

        navigationController = new NavigationController(mobileStationService, baseStationService);

        seed();

    }

    @Test
    void getMobileStation() throws Exception {

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].timestamp", Matchers.notNullValue()));

    }

    @Test
    void getBaseStation() throws Exception {

        BaseStationEntity baseStationEntity = baseStationEntities.get(0);

        mockMvc.perform(get("/" + baseStationEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("base_station_id", Matchers.notNullValue()))
                .andExpect(jsonPath("reports", Matchers.notNullValue()));

    }

    @Test
    void locationUpdate() {

        ObjectMapper objectMapper = new ObjectMapper();

        baseStationEntities.forEach(baseStationEntity -> {

            HearBeatDto beatDto = HearBeatDto.builder()
                    .uuid(baseStationEntity.getUuid())
                    .timestamp(Instant.now().toEpochMilli())
                    .build();

            try {

                mockMvc.perform(post("/locationUpdate/" + baseStationEntity.getId())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(beatDto)))
                        .andExpect(status().isOk());

            } catch (Exception e) {
                log.debug(e.getMessage());
            }
        });


    }

}