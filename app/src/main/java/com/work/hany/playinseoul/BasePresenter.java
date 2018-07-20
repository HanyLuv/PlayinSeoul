package com.work.hany.playinseoul;

public interface BasePresenter<T> {
    void loadContent(int contentId, int contentTypeId);
    void takeView(T view);
    void dropView();

}
