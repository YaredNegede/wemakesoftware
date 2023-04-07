package com.wemakesoftware.navigation.basestation;

import com.wemakesoftware.navigation.basestation.dto.BaseStationDTO;
import com.wemakesoftware.navigation.basestation.dto.ReportDTO;

import java.util.List;

public interface BaseStationService {

    ReportDTO getBaseStation(String uuid);

    List<BaseStationDTO> getBaseStations();
}
