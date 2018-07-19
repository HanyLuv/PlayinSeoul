package com.work.hany.playinseoul.model;

public class Section<T> {
    private ItemType type;
    private T data = null;

    public Section(ItemType type, T data) {
        this.data = data;
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public ItemType getType() {
        return type;
    }

    public void setData(T data) {
        this.data = data;
    }

    public enum ItemType {
        IMAGE(0), //이미지
        INTRO(1), //소개
        INFORMATION(2), //운영정보
        PHOTOS(3), // 사진들~
        MAP(4), //지도
        COURSE(5), // 코스 정보
        NOTHING(-1); //아무것도 아니다..

        private int code;

        ItemType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

}



