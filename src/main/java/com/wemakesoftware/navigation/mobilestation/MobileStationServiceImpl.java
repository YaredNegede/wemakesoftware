package com.wemakesoftware.navigation.mobilestation;

import com.wemakesoftware.navigation.basestation.BaseStationEntity;
import com.wemakesoftware.navigation.basestation.BaseStationEntityRepository;
import com.wemakesoftware.navigation.exceptions.NavigationException;
import com.wemakesoftware.navigation.mobilestation.dto.HearBeatDto;
import com.wemakesoftware.navigation.mobilestation.dto.MobileStationReportDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MobileStationServiceImpl implements MobileStationService {

    private final MobileStationEntityRepository mobileStationEntityRepository;

    private final BaseStationEntityRepository baseStationEntityRepository;

    public MobileStationServiceImpl(MobileStationEntityRepository mobileStationEntityRepository,
                                    BaseStationEntityRepository baseStationEntityRepository) {
        this.mobileStationEntityRepository = mobileStationEntityRepository;
        this.baseStationEntityRepository = baseStationEntityRepository;
    }

    @Override
    public MobileStationReportDTO getMobileStationLocation(String uuid) {

        Optional<MobileStationEntity> msDto = this.mobileStationEntityRepository.findByMobileId(uuid);
        if(!msDto.isPresent()){
            throw new NavigationException(NavigationException.NOT_FOUND);
        }
        MobileStationEntity ms = msDto.get();
        return new MobileStationReportDTO(ms.getMobileId(),ms.getLastKnownX(), ms.getLastKnownY());
    }

    @Override
    public void updateLocation(long bsuuid, HearBeatDto hearBeatDto) {

        Optional<MobileStationEntity>  ms = this.mobileStationEntityRepository.findByMobileId(hearBeatDto.getUuid());

        Optional<BaseStationEntity> bs = baseStationEntityRepository.findById(bsuuid);

       if(bs.isPresent()){

           MobileStationEntity foundMobileStation = ms.orElseGet(MobileStationEntity::new);

           BaseStationEntity foundBaseStation = bs.get();

           foundMobileStation.setLastKnownX(foundBaseStation.getX());

           foundMobileStation.setLastKnownY(foundBaseStation.getY());

           mobileStationEntityRepository.save(foundMobileStation);

       }

       else {

           throw new NavigationException(NavigationException.NOT_FOUND);

       }

    }

}
