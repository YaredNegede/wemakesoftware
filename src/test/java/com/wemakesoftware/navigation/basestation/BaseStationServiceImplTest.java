package com.wemakesoftware.navigation.basestation;

import com.wemakesoftware.navigation.basestation.dto.BaseStationDTO;
import com.wemakesoftware.navigation.basestation.dto.ReportDTO;
import com.wemakesoftware.navigation.mobilestation.MobileStationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.*;

public class BaseStationServiceImplTest {

    private BaseStationEntityRepository baseStationEntityRepository;

    private  BaseStationEntityMapper baseStationEntityMapper = new BaseStationEntityMapperImpl();

    private BaseStationService baseStationService;

    @BeforeEach
    public void setup(){
        this.baseStationEntityRepository = mock(BaseStationEntityRepository.class);
        this.baseStationService = new BaseStationServiceImpl(this.baseStationEntityRepository, this.baseStationEntityMapper);
    }

    @Test
    void getBaseStation() {
        BaseStationEntity bs1 = new BaseStationEntity(UUID.randomUUID().toString(), 10f, 5f, 5f);
        BaseStationEntity bs2 =new BaseStationEntity(UUID.randomUUID().toString(),10f,5f,5f);
        BaseStationEntity bs3 =new BaseStationEntity(UUID.randomUUID().toString(),10f,5f,5f);
        List<BaseStationEntity> bss = Arrays.asList(bs1,bs2,bs3);
        when(this.baseStationEntityRepository.findAll()).thenReturn(bss);
        List<BaseStationDTO> results = this.baseStationService.getBaseStations();

        assertThat(results.size()).isEqualTo(bss.size());
    }

    @Test
    void getBaseStations() {
        BaseStationEntity bs1 = new BaseStationEntity(UUID.randomUUID().toString(), 10f, 5f, 5f);
        List<MobileStationEntity> mss = new ArrayList<>();
        mss.add(new MobileStationEntity());
        mss.add(new MobileStationEntity());
        mss.add(new MobileStationEntity());
        bs1.getMobileStations().addAll(mss);

        when(this.baseStationEntityRepository.findByUuid(bs1.getUuid())).thenReturn(bs1);
        ReportDTO results = this.baseStationService.getBaseStation(bs1.getUuid());
        assertThat(results).isNotNull();
        assertThat(results.getBaseStationId()).isEqualTo(bs1.getUuid());
    }
}