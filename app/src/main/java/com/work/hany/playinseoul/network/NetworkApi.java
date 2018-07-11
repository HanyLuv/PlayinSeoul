package com.work.hany.playinseoul.network;

import javax.inject.Inject;

public class NetworkApi {


    @Inject //생성자에 대한 주석을 사용해 이 클래스의 객체를 다른 객체에 삽입 할수 있다고 지시함.
    public NetworkApi() { //Dagger는 이 클래스의 인스턴스가 필요한 경우 이 생성자를 자동으로 호출
    }


    public boolean validateUser(String username, String password){
        if (username == null || username.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
