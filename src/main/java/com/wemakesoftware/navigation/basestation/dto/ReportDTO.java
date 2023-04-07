package com.wemakesoftware.navigation.basestation.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "base_station_id",
        "reports"
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    @JsonProperty("base_station_id")
    private String baseStationId;

    @JsonProperty("reports")
    @Valid
    private Set<BaseStationReport> baseStationReportDTOS;

}