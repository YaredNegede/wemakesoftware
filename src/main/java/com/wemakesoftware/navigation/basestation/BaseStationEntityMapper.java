package com.wemakesoftware.navigation.basestation;

import com.wemakesoftware.navigation.basestation.dto.BaseStationDTO;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BaseStationEntityMapper {

    default UUID toUUID(String uuid){
        return UUID.fromString(uuid);
    }

    default String toString(UUID uuid){
        return uuid.toString();
    }

    BaseStationEntity toEntity(BaseStationDTO baseStationDTO);

    BaseStationDTO toDto(BaseStationEntity baseStationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BaseStationEntity partialUpdate(BaseStationDTO baseStationDTO, @MappingTarget BaseStationEntity baseStationEntity);

}