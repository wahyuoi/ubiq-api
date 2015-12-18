package com.share.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wahyuoi on 12/17/15.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @JsonProperty
    @Column(name = "nama")
    String nama;
    @JsonProperty
    @Column(name = "secret")
    String secret;
    @JsonProperty
    @Column(name = "lon")
    Double lon;
    @JsonProperty
    @Column(name = "lat")
    Double lat;
    @JsonProperty
    @Column(name = "device_id")
    String deviceId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    List<Uploaded> uploadedList = new ArrayList<>(0);

    public User() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<Uploaded> getUploadedList() {
        return uploadedList;
    }

    public void setUploadedList(List<Uploaded> uploadedList) {
        this.uploadedList = uploadedList;
    }
}
