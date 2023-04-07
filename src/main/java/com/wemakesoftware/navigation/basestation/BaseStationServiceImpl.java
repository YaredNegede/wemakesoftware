package com.wemakesoftware.navigation.basestation;

import com.wemakesoftware.navigation.basestation.dto.BaseStationDTO;
import com.wemakesoftware.navigation.basestation.dto.BaseStationReport;
import com.wemakesoftware.navigation.basestation.dto.ReportDTO;
import com.wemakesoftware.navigation.exceptions.NavigationException;
import com.wemakesoftware.navigation.mobilestation.MobileStationEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BaseStationServiceImpl implements BaseStationService {

    private final BaseStationEntityRepository baseStationEntityRepository;

    private final BaseStationEntityMapper baseStationEntityMapper;

    public BaseStationServiceImpl(BaseStationEntityRepository baseStationEntityRepository,
                                  BaseStationEntityMapper baseStationEntityMapper) {
        this.baseStationEntityRepository = baseStationEntityRepository;
        this.baseStationEntityMapper = baseStationEntityMapper;
    }

    @Override
    public ReportDTO getBaseStation(String uuid) {

        BaseStationEntity res = this.baseStationEntityRepository.findByUuid(uuid);

        if(null == res){
            throw new NavigationException(NavigationException.NOT_FOUND);
        }

        Set<BaseStationReport> msReport = res.getMobileStations()
                                            .stream()
                                            .map(mobileStationEntity -> mapToReport(res, mobileStationEntity))
                                            .collect(Collectors.toSet());
       
        return new ReportDTO(res.getUuid(), msReport);

    }

    protected BaseStationReport mapToReport(BaseStationEntity baseStationEntity, MobileStationEntity mobileStationEntity) {

        return BaseStationReport.builder()
                .mobileStationId(mobileStationEntity.getMobileId())
                .timestamp(Instant.ofEpochMilli(mobileStationEntity.getTimestamp()))
                .distance(calculateDistance(baseStationEntity, mobileStationEntity))
                .build();

    }

    protected static float calculateDistance(BaseStationEntity baseStationEntity, MobileStationEntity mobileStationEntity) {

        double ac = Math.abs(baseStationEntity.getY() - mobileStationEntity.getLastKnownY());

        double cb = Math.abs(baseStationEntity.getX() - mobileStationEntity.getLastKnownX());

        return (float) Math.hypot(ac, cb);

    }

    @Override
    public List<BaseStationDTO> getBaseStations() {

        return this.baseStationEntityRepository.findAll()
                .stream()
                .map(this.baseStationEntityMapper::toDto)
                .collect(Collectors.toList());

    }

}

