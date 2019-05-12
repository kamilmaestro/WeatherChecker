package com.kamilmarnik.weatherchecker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class InternetConnection {
    private Context context;

    public InternetConnection(Context context){
        this.context = context;
    }

    public boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if(activeNetwork != null && activeNetwork.isConnected())
            return true;
        else
            return false;
    }
}
