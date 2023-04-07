package com.wemakesoftware.navigation.mobilestation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileStationEntityRepository extends JpaRepository<MobileStationEntity, Long> {

    Optional<MobileStationEntity> findByMobileId(String bsuuid);

}