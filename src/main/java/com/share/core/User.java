package com.share.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wahyuoi on 12/17/15.
 */
public class User {
    @JsonProperty
    String nama;
    @JsonProperty
    String key;

    public User() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
