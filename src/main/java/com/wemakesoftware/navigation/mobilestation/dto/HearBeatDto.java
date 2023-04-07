package com.wemakesoftware.navigation.mobilestation.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class HearBeatDto {
    @NotEmpty(message = "mobile id cannot be empty")
    private String uuid;
    @NotEmpty(message = "mobile id cannot be empty")
    private Long timestamp;
}
