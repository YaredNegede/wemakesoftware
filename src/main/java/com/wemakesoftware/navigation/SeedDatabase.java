package com.wemakesoftware.navigation;

import com.wemakesoftware.navigation.basestation.BaseStationEntity;
import com.wemakesoftware.navigation.basestation.BaseStationEntityRepository;
import com.wemakesoftware.navigation.mobilestation.MobileStationEntity;
import com.wemakesoftware.navigation.mobilestation.MobileStationEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.IntStream;

@Slf4j
@Component
public class SeedDatabase implements ApplicationRunner {

    private final BaseStationEntityRepository baseStationEntityRepository;

    private final MobileStationEntityRepository mobileStationEntityRepository;

    public SeedDatabase(BaseStationEntityRepository baseStationEntityRepository, MobileStationEntityRepository mobileStationEntityRepository) {
        this.baseStationEntityRepository = baseStationEntityRepository;
        this.mobileStationEntityRepository = mobileStationEntityRepository;
    }

    /**
     * do not consider the quality of this code.
     * @throws IOException
     */
    private void seed() throws IOException {

        for (float i = -5; i <= 5f; i++) {

            for (float j = -5; j <= 5f; j++) {

                BaseStationEntity baseStationEntity = new BaseStationEntity(
                        UUID.randomUUID().toString(),
                        3f,
                        i*10,
                        j*10
                );

                MobileStationEntity mstation = new MobileStationEntity( i*10, j*10, Instant.now().toEpochMilli(), UUID.randomUUID().toString());

                mobileStationEntityRepository.save(mstation);

                baseStationEntity.getMobileStations().add(mstation);

                baseStationEntityRepository.save(baseStationEntity);

            }

        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seed();
    }
}
