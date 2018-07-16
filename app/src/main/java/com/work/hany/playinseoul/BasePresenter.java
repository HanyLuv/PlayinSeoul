package com.work.hany.playinseoul;

public interface BasePresenter<T> {

    void takeView(T view);
    void dropView();
}
