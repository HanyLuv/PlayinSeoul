package com.work.hany.playinseoul.network.network

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayInSeoulService {
    //지역기반 관광정보 조회
    @GET("openapi/service/rest/KorService/areaBasedList")
    fun getAreaBasedList(): Call<Result>


    // 키워드 검색

    // 공통정보 조회

    // 이미지 정보 조회
}


data class Result(@SerializedName("response") var response: Response)


data class Response(@SerializedName("header") var header: Header,
                    @SerializedName("body") var body: Body)


data class Header(@SerializedName("resultCode") var resultCode: String,
                  @SerializedName("resultMsg") var resultMessage: String)



data class Body(@SerializedName("items") var areaTourInformationList: ArrayList<AreaTourInformation>,
                  @SerializedName("numOfRows") var numOfRows: Int,
                  @SerializedName("pageNo") var pageNo: Int,
                 @SerializedName("totalCount") var totalCount: Int)


/**
*
 *
*
* */

/**
 *
"addr1": "서울특별시 강서구 곰달래로 247",
"addr2": "(화곡동)",
"areacode": 1,
"cat1": "B02",
"cat2": "B0201",
"cat3": "B02010900",
"contentid": 1747824,
"contenttypeid": 32,
"createdtime": 20121105144638,
"firstimage": "http://tong.visitkorea.or.kr/cms/resource/22/1744722_image2_1.jpg",
"firstimage2": "http://tong.visitkorea.or.kr/cms/resource/22/1744722_image3_1.jpg",
"mapx": 126.8605049692,
"mapy": 37.5321644636,
"mlevel": 6,
"modifiedtime": 20170518151707,
"readcount": 16166,
"sigungucode": 4,
"tel": "02-2643-8800",
"title": "㈜코스테이",
"zipcode": "07741"

 * */

data class AreaTourInformation(
        @SerializedName("addr1") var fullAddress: String,
        @SerializedName("addr2") var areaAddress: String,
        @SerializedName("areacode") var areacode: String,
        @SerializedName("cat1") var largeCategory: String,
        @SerializedName("cat2") var mediumCategory: String,
        @SerializedName("cat3") var smallCategory: String,
        @SerializedName("contentid") var contentID: Int,
        @SerializedName("contenttypeid") var contentTypeID: Int, //관광타입
        @SerializedName("createdtime") var createdTime: Int,
        @SerializedName("firstimage") var largeImage: String,
        @SerializedName("firstimage2") var smallImage: String,
        @SerializedName("mapx") var mapx: Float, //경도
        @SerializedName("mapy") var mapy: Float, //위도
        @SerializedName("mlevel") var mapLevel: Int,
        @SerializedName("modifiedtime") var modifiedTime: Int,
        @SerializedName("readcount") var readCount: Int, //조회수
        @SerializedName("sigungucode") var sigunguCode: Int, //시군코드
        @SerializedName("tel") var tel: String, //연락처
        @SerializedName("title") var contentTitle: String,
        @SerializedName("zipcode") var zipCode: String //우편 번호


)
