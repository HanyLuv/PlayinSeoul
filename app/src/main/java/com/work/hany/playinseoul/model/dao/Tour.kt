package com.work.hany.playinseoul.model.dao


import com.google.gson.annotations.SerializedName

//관광지 12 detailIntro 요청 결과값
data class TourIntro (
        @SerializedName("contenttypeid") var contentTypeId: String, //수용 인원
        @SerializedName("contentid") var contentId: String, //수용 인원
        @SerializedName("accomcount") var accomcount: String, //수용 인원
        @SerializedName("chkbabycarriage") var hasBabyCarriage: String, //유모차 대여 여부  //없음
        @SerializedName("chkcreditcard") var subContentId: String, //신용카드 여부
        @SerializedName("chkpet") var isPetPossible: String, //애완동물출입 가능여부~
        @SerializedName("expagerange") var expagerange: String, //체험 가능연령
        @SerializedName("expguide") var expGuide: String, //체험안내
        @SerializedName("heritage1") var heritage1: Int, //세계문화유산 유무 // 0, 1
        @SerializedName("heritage2") var heritage2: Int, //세계자연유산유무 // 0, 1
        @SerializedName("heritage3") var heritage3: Int, //세계 기록 유산 유무 //0, 1
        @SerializedName("infocenter") var infoCenter: String, //문의 및 안내 연락처
        @SerializedName("opendate") var openDate: String, //개장일
        @SerializedName("parking") var parking: String, //주차시설 // 있음
        @SerializedName("useseason") var useSeason: String, //이용시기
        @SerializedName("restdate") var restDate: String, //쉬는날 //"주말 및 공휴일",
        @SerializedName("usetime") var useTime: String //이용시간 //"평일 10:00~14:00" //<br /> 이렇게들 내려와 ㅠㅠ ㅅㅂ
)

//숙박, 여행코스 제외한 타입이라함.... @_@;;;
data class TourDetail (
        @SerializedName("contentid") var contentTypeId: Int,
        @SerializedName("contenttypeid") var contentId: Int,
        @SerializedName("fldgubun") var fldgubun: Int, // 일련번호인데 잘모르겠음 ㅠ
        @SerializedName("infoname") var informationTitle: String, // 제목
        @SerializedName("infotext") var informationDescription: String, // 설명...
        @SerializedName("serialnum") var serialNumber: Int //반복일련번호
)
data class StayDetail (
        var serialNumber: Int, //반복일련번호
        var serialMaxNumber: Int, //반복일련최대값
        var isRoomOverMaxCount: Boolean, //룸은 4개만 보여주는데 그 항목을 넘는가에 대한 여부
        @SerializedName("contentid") var contentTypeId: Int,
        @SerializedName("contenttypeid") var contentId: Int,
        @SerializedName("roomaircondition") var roomAirCondition: String, // 에어
        @SerializedName("roomcode") var roomCode: Int, // 객실코드
        @SerializedName("roomtitle") var roomTitle: String, //객실명...
        @SerializedName("roomsize1") var roomSize1: Int, //객실크기(평)
        @SerializedName("roomsize2") var roomSize2: Int, //객실크기(평방미)
        @SerializedName("roomcount") var roomCount: Int, //객실수
        @SerializedName("roombasecount") var roomBaseCount: Int, //기준인원
        @SerializedName("roommaxcount") var roomMaxCount: Int, //최대 인원
        @SerializedName("roomoffseasonminfee1") var roomOffSeasonMinFee1: Int, //비수기 주중 최소
        @SerializedName("roomoffseasonminfee2") var roomOffSeasonMinFee2: Int, //비수기 주말 최소
        @SerializedName("roompeakseasonminfee1") var roomPeakSeasonMinFee1: Int, //성수기 주중 최소
        @SerializedName("roompeakseasonminfee2") var roomPeakSeasonMinFee2: Int, //성수기 주말 최
        @SerializedName("roomintro") var roomIntro: String, //객실소개
        @SerializedName("roombathfacility") var roomBathFacility: String, //목욕시설
        @SerializedName("roombath") var roomBath: String, //욕조
        @SerializedName("roomhometheater") var roomHomeTheater: String, //홈시어터
        @SerializedName("roomtv") var roomTV: String, //티비
        @SerializedName("roompc") var roomPC: String, //컴퓨터
        @SerializedName("roomcable") var roomCable: String, //케이블여부
        @SerializedName("roominternet") var roomInternet: String, //인터넷
        @SerializedName("roomrefrigerator") var roomRefrigerator: String, //냉장고
        @SerializedName("roomtoiletries") var roomToiletRies: String, //세면도구
        @SerializedName("roomsofa") var roomSofa: String, //소파
        @SerializedName("roomcook") var roomCook: String, //취사용품
        @SerializedName("roomTable") var roomTable: String, //테이블
        @SerializedName("roomHairdryer") var roomHairDryer: String //헤어드라이기

)
