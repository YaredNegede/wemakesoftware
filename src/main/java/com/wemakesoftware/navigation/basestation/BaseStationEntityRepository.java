package com.wemakesoftware.navigation.basestation;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface BaseStationEntityRepository extends JpaRepository<BaseStationEntity, Long> {

    Optional<BaseStationEntity> findByUuid(String uuid);

}