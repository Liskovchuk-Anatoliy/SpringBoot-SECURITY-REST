package ru.itmentor.spring.boot_security.demo.util;

public class UserErrorResponse {
    private String meesege;
    private long timestamp;

    public UserErrorResponse(String meesege, long timestamp) {
        this.meesege = meesege;
        this.timestamp = timestamp;
    }

    public String getMeesege() {
        return meesege;
    }

    public void setMeesege(String meesege) {
        this.meesege = meesege;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
