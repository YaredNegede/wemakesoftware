package com.wemakesoftware.navigation.basestation.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "mobile_station_id",
        "distance",
        "timestamp"
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseStationReport {

    @JsonProperty("mobile_station_id")
    private String mobileStationId;
    @JsonProperty("distance")
    private float distance;
    @JsonProperty("timestamp")
    private Instant timestamp;

}