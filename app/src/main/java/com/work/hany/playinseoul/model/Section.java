package com.work.hany.playinseoul.model;

public class Section<T> {
    private ItemType type;
    private T data;

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
        OVERHEAD(1), //소개
        INFORMATION(2), //운영정보
        PHOTOS(3), // 사진들~
        MAP(4), //지도
        DETAIL(5), // 여행 : 코스정보 / 관광지 / 입장정보등등
        STAY_DETAIL(6), // 숙박의 디테일은 다르기때문에ㅣ..

        /** 메인화면에서 쓰이는 아이템임 'ㅅ' */
        CATEGORY(7), //메인의 카테고리
        MAIN_TOUR(8), //메인의 일반 아이템들@_@


        NOTHING(-1);//아무것도 아니다..

        private int code;

        ItemType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

}



