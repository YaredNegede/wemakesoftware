package com.wemakesoftware.navigation.basestation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseStationEntityRepository extends JpaRepository<BaseStationEntity, Long> {

    BaseStationEntity findByUuid(String uuid);
}