package com.wemakesoftware.navigation.basestation;

import com.wemakesoftware.navigation.basestation.dto.BaseStationDTO;
import com.wemakesoftware.navigation.basestation.dto.BaseStationReport;
import com.wemakesoftware.navigation.basestation.dto.ReportDTO;
import com.wemakesoftware.navigation.exceptions.NavigationException;
import com.wemakesoftware.navigation.mobilestation.MobileStationEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        Optional<BaseStationEntity> res = this.baseStationEntityRepository.findByUuid(uuid);

        if(!res.isPresent()){
            throw new NavigationException(NavigationException.NOT_FOUND);
        }

        Set<BaseStationReport> msReport = res.get()
                                            .getMobileStations()
                                            .stream()
                                            .map(mobileStationEntity -> mapToReport(res.get(), mobileStationEntity))
                                            .collect(Collectors.toSet());
       
        return new ReportDTO(res.get().getUuid(), msReport);

    }

    protected BaseStationReport mapToReport(BaseStationEntity baseStationEntity, MobileStationEntity mobileStationEntity) {

        return BaseStationReport.builder()
                .mobileStationId(mobileStationEntity.getMobileId())
                .timestamp(Instant.ofEpochMilli(mobileStationEntity.getTimestamp()))
                .distance(calculateDistance(baseStationEntity, mobileStationEntity))
                .build();

    }

    protected float calculateDistance(BaseStationEntity baseStationEntity, MobileStationEntity mobileStationEntity) {

        double ac = Math.abs(baseStationEntity.getY() - mobileStationEntity.getLastKnownY());

        double cb = Math.abs(baseStationEntity.getX() - mobileStationEntity.getLastKnownX());

        return (float) Math.hypot(ac, cb);

    }

    @Override
    public List<BaseStationDTO> getBaseStations() {

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "X"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "Y"));

        return this.baseStationEntityRepository.findAll(Sort.by(orders))
                .stream()
                .map(this.baseStationEntityMapper::toDto)
                .collect(Collectors.toList());

    }

}

