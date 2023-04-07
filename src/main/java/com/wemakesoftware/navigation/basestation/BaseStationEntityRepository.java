package com.wemakesoftware.navigation.basestation;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BaseStationEntityRepository extends JpaRepository<BaseStationEntity, Long> {

    BaseStationEntity findByUuid(String uuid);
}