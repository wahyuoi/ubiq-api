package com.share.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by wahyuoi on 12/23/15.
 */
public class DataRegister {
    @JsonProperty
    String deviceType;
    @JsonProperty
    String deviceToken;
    @JsonProperty
    String pushType;
    @JsonProperty
    ArrayList<String> channels = new ArrayList<>();

    public DataRegister() {
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public ArrayList<String> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<String> channels) {
        this.channels = channels;
    }
}
