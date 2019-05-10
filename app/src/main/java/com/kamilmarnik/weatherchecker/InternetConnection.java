package com.kamilmarnik.weatherchecker;

import java.io.IOException;

public class InternetConnection {
    private boolean isOnline;

    public InternetConnection(){}

    public InternetConnection(boolean isOnline){
        this.isOnline = isOnline;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
