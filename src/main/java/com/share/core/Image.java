package com.share.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wahyuoi on 12/18/15.
 */
public class Image {
    @JsonProperty
    String base64;
    @JsonProperty
    String ext;
    @JsonProperty
    String deviceId;

    public Image() {
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
