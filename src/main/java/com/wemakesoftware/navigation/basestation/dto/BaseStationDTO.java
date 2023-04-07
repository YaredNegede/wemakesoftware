package com.wemakesoftware.navigation.basestation.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class BaseStationDTO {

    private String uuid;

    private Long timestamp = Instant.now().toEpochMilli();

    private float detectionRadiusInMeters;

    private float x;

    private float y;

}
