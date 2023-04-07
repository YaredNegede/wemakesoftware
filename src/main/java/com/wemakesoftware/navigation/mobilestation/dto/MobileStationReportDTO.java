package com.wemakesoftware.navigation.mobilestation.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "mobileId",
        "x",
        "y",
        "error_radius",
        "error_code",
        "error_description"
})
@Data
public class MobileStationReportDTO {
    public MobileStationReportDTO(String mobileId, float x, float y) {
        this.mobileId = mobileId;
        this.x = x;
        this.y = y;
    }

    @JsonProperty("mobileId")
    private String mobileId;
    @JsonProperty("x")
    private float x;
    @JsonProperty("y")
    private float y;
    @JsonProperty("error_radius")
    private float errorRadius;
    @JsonProperty("error_code")
    private Integer errorCode;
    @JsonProperty("error_description")
    private String errorDescription;
}
