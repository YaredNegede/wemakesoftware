package com.wemakesoftware.navigation.mobilestation;

import com.wemakesoftware.navigation.mobilestation.dto.HearBeatDto;
import com.wemakesoftware.navigation.mobilestation.dto.MobileStationReportDTO;

public interface MobileStationService {

    MobileStationReportDTO getMobileStationLocation(String uuid);

    void updateLocation(long bsuuid, HearBeatDto hearBeatDto);
}
