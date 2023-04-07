package com.wemakesoftware.navigation.basestation;

import com.wemakesoftware.navigation.mobilestation.MobileStationEntity;
import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "base_station")
public class BaseStationEntity {

    public BaseStationEntity() {
    }

    public BaseStationEntity(String uuid, Float detectionRadiusInMeters, Float x, Float y) {
        this.uuid = uuid;
        this.detectionRadiusInMeters = detectionRadiusInMeters;
        this.x = x;
        this.y = y;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid",unique = true)
    private String uuid;

    @Builder.Default
    private Long timestamp = Instant.now().toEpochMilli();

    @Column(name = "detection_radius")
    private Float detectionRadiusInMeters;

    @Column(name = "x_coordinate")
    private Float x;

    @Column(name = "y_coordinate")
    private Float y;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "base_station_mobile_station",
            joinColumns = @JoinColumn(name = "base_station_id"),
            inverseJoinColumns = @JoinColumn(name = "mobile_station_id"))
    private Set<MobileStationEntity> mobileStations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Float getDetectionRadiusInMeters() {
        return detectionRadiusInMeters;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public String getUuid() {
        return uuid;
    }

    public Set<MobileStationEntity> getMobileStations() {
        return mobileStations;
    }
}
