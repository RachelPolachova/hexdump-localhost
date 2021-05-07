package com.example.hexdump.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HexdumpRequest {
    private List<Hexdump> traffic;

    public HexdumpRequest(@JsonProperty("traffic") List<Hexdump> traffic) {
        this.traffic = traffic;
    }

    public List<Hexdump> getTraffic() {
        return traffic;
    }

    public void setTraffic(List<Hexdump> traffic) {
        this.traffic = traffic;
    }

    public String toString() {
        StringBuilder resultStr = new StringBuilder();

        for (Hexdump hexdump : traffic) {
            resultStr.append("\n");
            resultStr.append("0 ").append(hexdump.getHex());
        }

        return resultStr.toString();
    }
}
