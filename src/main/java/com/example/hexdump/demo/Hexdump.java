package com.example.hexdump.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hexdump {
    private String hex;

    public Hexdump(@JsonProperty("hex") String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
