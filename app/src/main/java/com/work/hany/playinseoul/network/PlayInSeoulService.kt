package com.work.hany.playinseoul.network

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayInSeoulService {
    //지역기반 관광정보 조회
    @GET("areaBasedList")
    fun getAreaBasedList(): Call<Result>

    //detailCommon	공통정보 조회 (상세정보1) 여행코스는 주소가 없다 -_-;;아오 진짜 아래 링크는 문화시설 code 14
    // http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey=Ejx4tOEJrUzj0J460Snt4dNSCkA0H%2FINuX8Bvec4EMrJJieFwDCHJdL%2BVU%2B6HpuR2nrHrqG8ziZj%2FZ5gwGo0yg%3D%3D&MobileApp=PlayInSeoul&MobileOS=AND&_type=json&contentId=129854&contentTypeId=14&overviewYN=Y&addrinfoYN=Y&mapinfoYN=Y
    @GET("detailCommon?overviewYN=Y&addrinfoYN=Y&mapinfoYN=Y")
    fun getTourOperation(): Call<Result>

    /**
    @description 소개정보 조회 (상세정보2)
    타입별 소개정보(휴무일, 개장시간, 주차시설 등)를 조회하는 기능입니다.
    각 타입마다 응답 항목이 다르게 제공됩니다. 
     */
    @GET("detailIntro?contentId={contentId}&contenttypeId={contenttypeid}")
    fun getTourInrtro(@Path("contentId") contentID: Int, @Path("contenttypeid") contentTypeID: Int): Call<Result>
//    10		detailInfo	반복정보 조회 (상세정보3)
//    11		detailImage	이미지정보 조회 (상세정보4)


}


data class Result(@SerializedName("response") var response: Response)


data class Response(@SerializedName("header") var header: Header,
                    @SerializedName("body") var body: Body)


data class Header(@SerializedName("resultCode") var resultCode: String,
                  @SerializedName("resultMsg") var resultMessage: String)



data class Body(@SerializedName("items") var items: Items,
                  @SerializedName("numOfRows") var numOfRows: Int,
                  @SerializedName("pageNo") var pageNo: Int,
                 @SerializedName("totalCount") var totalCount: Int)

data class Items(@SerializedName("item") var areaTourInformationList: ArrayList<AreaTourInformation>)


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

data class AreaTourInformation (
        @SerializedName("addr1") var fullAddress: String,
        @SerializedName("addr2") var areaAddress: String,
        @SerializedName("areacode") var areaCode: String,
        @SerializedName("cat1") var largeCategory: String,
        @SerializedName("cat2") var mediumCategory: String,
        @SerializedName("cat3") var smallCategory: String,
        @SerializedName("contentid") var contentID: Int,
        @SerializedName("contenttypeid") var contentTypeID: Int, //관광타입
        @SerializedName("createdtime") var createdTime: Long,
        @SerializedName("firstimage") var largeImage: String,
        @SerializedName("firstimage2") var smallImage: String,
        @SerializedName("mapx") var mapx: Float, //경도
        @SerializedName("mapy") var mapy: Float, //위도
        @SerializedName("mlevel") var mapLevel: Int,
        @SerializedName("modifiedtime") var modifiedTime: Long,
        @SerializedName("readcount") var readCount: Int, //조회수
        @SerializedName("sigungucode") var sigunguCode: Int, //시군코드
        @SerializedName("tel") var tel: String, //연락처
        @SerializedName("title") var contentTitle: String,
        @SerializedName("zipcode") var zipCode: String //우편 번호


) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readFloat(),
            source.readFloat(),
            source.readInt(),
            source.readLong(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(if (fullAddress == null) "" else fullAddress)
        writeString(if (areaAddress == null) "" else areaAddress)
        writeString(if (areaCode == null) "" else areaCode)
        writeString(if (largeCategory == null) "" else largeCategory)
        writeString(if (mediumCategory == null) "" else mediumCategory)
        writeString(if (smallCategory == null) "" else smallCategory)
        writeInt(contentID)
        writeInt(contentTypeID)
        writeLong(createdTime)
        writeString(if (largeImage == null) "" else largeImage)
        writeString(if (smallImage == null) "" else smallImage)
        writeFloat(mapx)
        writeFloat(mapy)
        writeInt(mapLevel)
        writeLong(modifiedTime)
        writeInt(readCount)
        writeInt(sigunguCode)
        writeString(if (tel == null) "" else tel)
        writeString(if (contentTitle == null) "" else contentTitle)
        writeString(if (zipCode == null) "" else zipCode)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AreaTourInformation> = object : Parcelable.Creator<AreaTourInformation> {
            override fun createFromParcel(source: Parcel): AreaTourInformation = AreaTourInformation(source)
            override fun newArray(size: Int): Array<AreaTourInformation?> = arrayOfNulls(size)
        }
    }
}