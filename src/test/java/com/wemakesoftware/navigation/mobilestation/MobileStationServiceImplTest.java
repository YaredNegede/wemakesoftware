package com.wemakesoftware.navigation.mobilestation;

import com.wemakesoftware.navigation.basestation.BaseStationEntity;
import com.wemakesoftware.navigation.basestation.BaseStationEntityRepository;
import com.wemakesoftware.navigation.mobilestation.dto.HearBeatDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MobileStationServiceImplTest {

    private MobileStationServiceImpl mobileStationService;
    
    private List<BaseStationEntity> baseStationEntitySet = new ArrayList<>();

    private MobileStationEntityRepository mobileStationEntityRepository;

    private BaseStationEntityRepository baseStationEntityRepository;

    @BeforeEach
    public void setup(){

        mobileStationEntityRepository = mock(MobileStationEntityRepository.class);

        baseStationEntityRepository = mock(BaseStationEntityRepository.class);

        mobileStationService  = new MobileStationServiceImpl(mobileStationEntityRepository, baseStationEntityRepository);

    }

    @Test
    void getMobileStationLocation() {

        String uuid = UUID.randomUUID().toString();

        MobileStationEntity mock = mock(MobileStationEntity.class);

        Optional<MobileStationEntity> ms =  Optional.of(mock);

        when(mobileStationEntityRepository.findByMobileId(any())).thenReturn(ms);

        mobileStationService.getMobileStationLocation(uuid);

        verify(mobileStationEntityRepository, atLeastOnce()).findByMobileId(any());

    }

    @Test
    void updateLocation() {

        HearBeatDto hearBeatDto = HearBeatDto.builder()
                .timestamp(Instant.EPOCH.toEpochMilli())
                .uuid(UUID.randomUUID().toString())
                .build();

        String uuid = "12";

        BaseStationEntity mock = mock(BaseStationEntity.class);

        Optional<BaseStationEntity> bs  =  Optional.of(mock);

        when(baseStationEntityRepository.findById(any())).thenReturn(bs);

        mobileStationService.updateLocation(uuid, hearBeatDto);

        verify(baseStationEntityRepository, atLeastOnce()).findById(any());

        verify(mobileStationEntityRepository, atLeastOnce()).save(any());

    }

}