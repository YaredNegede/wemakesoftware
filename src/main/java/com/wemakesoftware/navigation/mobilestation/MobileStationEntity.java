package com.wemakesoftware.navigation.mobilestation;


import com.wemakesoftware.navigation.basestation.BaseStationEntity;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "mobile_station")
public class MobileStationEntity {

    public MobileStationEntity() {
    }

    public MobileStationEntity(float lastKnownX, float lastKnownY, long timestamp, String mobileId) {
        this.lastKnownX = lastKnownX;
        this.lastKnownY = lastKnownY;
        this.timestamp = timestamp;
        this.mobileId = mobileId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_known_x")
    private float lastKnownX;

    @Column(name = "last_known_y")
    private float lastKnownY;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "mobile_id",unique = true)
    private String mobileId;

    @Builder.Default
    @ManyToMany(mappedBy = "mobileStations")
    private Set<BaseStationEntity> mobileStations = new HashSet<>();

    public float getLastKnownX() {
        return lastKnownX;
    }

    public void setLastKnownX(float lastKnownX) {
        this.lastKnownX = lastKnownX;
    }

    public float getLastKnownY() {
        return lastKnownY;
    }

    public void setLastKnownY(float lastKnownY) {
        this.lastKnownY = lastKnownY;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMobileId() {
        return mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public void setMobileStations(Set<BaseStationEntity> mobileStations) {
        this.mobileStations = mobileStations;
    }

    public Set<BaseStationEntity> getMobileStations() {
        return mobileStations;
    }
}
