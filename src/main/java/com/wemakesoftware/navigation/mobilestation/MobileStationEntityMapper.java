package com.wemakesoftware.navigation.mobilestation;

import org.mapstruct.*;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MobileStationEntityMapper {

    default UUID toUUID(String uuid){
        return UUID.fromString(uuid);
    }
    default String toString(UUID uuid){
        return uuid.toString();
    }

}