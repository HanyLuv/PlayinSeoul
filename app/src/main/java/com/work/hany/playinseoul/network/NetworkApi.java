package com.work.hany.playinseoul.network;

import javax.inject.Inject;

public class NetworkApi {


    @Inject
    public NetworkApi() {
    }


    public boolean validateUser(String username, String password){
        if (username == null || username.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
