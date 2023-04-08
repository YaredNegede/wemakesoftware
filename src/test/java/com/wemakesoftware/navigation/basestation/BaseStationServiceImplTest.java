package com.wemakesoftware.navigation.basestation;

import com.wemakesoftware.navigation.mobilestation.MobileStationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BaseStationServiceImplTest {

    private BaseStationServiceImpl baseStationService;

    private BaseStationEntityRepository baseStationEntityRepository;

    private BaseStationEntityMapper baseStationEntityMapper = new BaseStationEntityMapperImpl();

    @BeforeEach
    public void setup(){

        baseStationEntityRepository = mock(BaseStationEntityRepository.class);

        baseStationService = new BaseStationServiceImpl(baseStationEntityRepository, baseStationEntityMapper);

    }

    @Test
    void getBaseStation() {

        BaseStationEntity mock = mock(BaseStationEntity.class);

        when(baseStationEntityRepository.findByUuid(any())).thenReturn(mock);

        baseStationService.getBaseStation("");

        verify(baseStationEntityRepository, atLeastOnce()).findByUuid(anyString());

    }

    @Test
    void getBaseStations() {

        BaseStationEntity mock = mock(BaseStationEntity.class);

        List<BaseStationEntity> data = new ArrayList<>();

        data.add(mock);

        when(baseStationEntityRepository.findAll()).thenReturn(data);

        baseStationService.getBaseStations();

        verify(baseStationEntityRepository, atLeastOnce()).findAll();

    }

    @Test
    void calculateDistance(){

        BaseStationEntity bs = new BaseStationEntity("", 1f,2f,2f);

        MobileStationEntity ms = new MobileStationEntity(2f, 2f, Instant.now().toEpochMilli(), "");

        float distance = baseStationService.calculateDistance(bs, ms);

        assertThat(distance).isEqualTo(0);

        bs = new BaseStationEntity("", 1f,0f,0f);

        distance = baseStationService.calculateDistance(bs, ms);

        assertThat(distance).isEqualTo(2.828427f);

    }

}
