package com.wemakesoftware.navigation;

import com.wemakesoftware.navigation.basestation.BaseStationService;
import com.wemakesoftware.navigation.basestation.dto.BaseStationDTO;
import com.wemakesoftware.navigation.basestation.dto.ReportDTO;
import com.wemakesoftware.navigation.mobilestation.MobileStationService;
import com.wemakesoftware.navigation.mobilestation.dto.HearBeatDto;
import com.wemakesoftware.navigation.mobilestation.dto.MobileStationReportDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NavigationController {

    private final MobileStationService mobileStationService;

    private final BaseStationService baseStationService;

    public NavigationController(MobileStationService stationService, BaseStationService baseStationService) {
        this.mobileStationService = stationService;
        this.baseStationService = baseStationService;
    }

    @Operation(
            summary = "Get all mobile station"
    )
    @GetMapping(value = "/location/{uuid}", produces = "application/json")
    public ResponseEntity<MobileStationReportDTO> getMobileStation(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(this.mobileStationService.getMobileStationLocation(uuid));
    }

    @Operation(
            summary = "Get all mobile station"
    )
    @PostMapping(value = "/location/{bsuuid}", produces = "application/json")
    public ResponseEntity<Void> locationUpdate(@PathVariable("bsuuid") String bsuuid,
                                               @RequestBody HearBeatDto hearBeatDto
    ) {
        this.mobileStationService.updateLocation(bsuuid,hearBeatDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get base station"
    )
    @GetMapping(value = "/basesStations/{uuid}", produces = "application/json")
    public ResponseEntity<ReportDTO> getBaseStation(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(this.baseStationService.getBaseStation(uuid));
    }

    @Operation(
            summary = "Get base station"
    )
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<BaseStationDTO>> getBaseStations() {
        return ResponseEntity.ok(this.baseStationService.getBaseStations());
    }

}
