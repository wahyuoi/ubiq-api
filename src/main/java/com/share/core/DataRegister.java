package com.share.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wahyuoi on 12/23/15.
 */
public class DataRegister {
    @JsonProperty
    String deviceType;
    @JsonProperty
    String deviceToken;

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
}
