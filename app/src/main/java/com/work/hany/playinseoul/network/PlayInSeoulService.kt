package com.work.hany.playinseoul.network

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.work.hany.playinseoul.model.dao.TourDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayInSeoulService {
    //지역기반 관광정보 조회 contentTypeId= 넣어서 다양한값 부를수잇다
    @GET("areaBasedList?&numOfRows=100")
    fun getAreaBasedList(): Call<Result<ArrayList<AreaTour>>>

    //detailCommon	공통정보 조회 (상세정보1) 여행코스는 주소가 없다 -_-;;아오 진짜 아래 링크는 문화시설 code 14
    // http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey=Ejx4tOEJrUzj0J460Snt4dNSCkA0H%2FINuX8Bvec4EMrJJieFwDCHJdL%2BVU%2B6HpuR2nrHrqG8ziZj%2FZ5gwGo0yg%3D%3D&MobileApp=PlayInSeoul&MobileOS=AND&_type=json&contentId=129854&contentTypeId=14&overviewYN=Y&addrinfoYN=Y&mapinfoYN=Y
    @GET("detailCommon?overviewYN=Y&addrinfoYN=Y&mapinfoYN=Y3&defaultYN=Y")
    fun getTourOverView(@Query("contentId") contentID: Int, @Query("contenttypeId") contentTypeID: Int): Call<Result<AreaTour>>

    /**
    @description 여행코스의 소개정보 조회
    타입별 소개정보(휴무일, 개장시간, 주차시설 등)를 조회하는 기능입니다.
    각 타입마다 응답 항목이 다르게 제공됩니다.
     */
    @GET("detailIntro?introYN=Y")
    fun getTravelInformation(@Query("contentId") contentID: Int, @Query("contentTypeId") contentTypeID: Int): Call<Result<TravelIntro>>

    /** @description 여행코스의 소개정보 조회여행정보 TravelDetail*/
    @GET("detailInfo?detailYN=Y")
    fun getTravelDetailInfo(@Query("contentId") contentID: Int, @Query("contentTypeId") contentTypeID: Int): Call<Result<ArrayList<TravelDetail>>>

    //    11		detailImage	이미지정보 조회 (상세정보4)
    @GET("detailImage")
    fun getTourPhotos(@Query("contentId") contentID: Int, @Query("contenttypeId") contentTypeID: Int): Call<Result<ArrayList<TourPhoto>>>


    //TODO 각 콘텐츠타입별 요청 서비스를 나뉘자 ~~

    /**
    @description 관광지 소개정보 조회 TourDetail
     */
    @GET("detailInfo?detailYN=Y")
    fun getDetailInfo(@Query("contentId") contentID: Int, @Query("contentTypeId") contentTypeID: Int): Call<Result<ArrayList<TourDetail>>>

}


data class Result<T>(@SerializedName("response") var response: Response<T>)


data class Response<T>(@SerializedName("header") var header: Header,
                    @SerializedName("body") var body: Body<T>)


data class Header(@SerializedName("resultCode") var resultCode: String,
                  @SerializedName("resultMsg") var resultMessage: String)



data class Body<T>(@SerializedName("items") var items: Items<T>,
                  @SerializedName("numOfRows") var numOfRows: Int,
                  @SerializedName("pageNo") var pageNo: Int,
                 @SerializedName("totalCount") var totalCount: Int)

data class Items<T>(@SerializedName("item") var data: T)


data class TourPhoto(@SerializedName("contentid") var contentId: Int,
                     @SerializedName("originimgurl") var originImageURI: String,
                     @SerializedName("serialnum") var serialNumber: String,
                     @SerializedName("smallimageurl") var smallImageURI: Int)
/**
 *
private const val CONTENT_TYPE_TOUR = 12 //관광지
private const val CONTENT_TYPE_CULTUR = 14 //문화시설
private const val CONTENT_TYPE_PESTIVAL = 15 //행사/공연/축제
private const val CONTENT_TYPE_TRAVEL_COURSE = 25 //여행코스
private const val CONTENT_TYPE_REPORTS = 28 //레포츠
private const val CONTENT_TYPE_STAY = 32//숙박
private const val CONTENT_TYPE_SHOPPING = 38//쇼핑
private const val CONTENT_TYPE_FOOD = 39//음식점
 * */


//여행 코스 디테일
data class TravelDetail (
        @SerializedName("contentid") var contentId: Int,
        @SerializedName("contenttypeid") var contentTypeId: Int,
        @SerializedName("subcontentid") var subContentId: Int,
        @SerializedName("subdetailalt") var subImageDescription: String, //코스이미지설명
        @SerializedName("subdetailimg") var subDetailImage: String,
        @SerializedName("subdetailoverview") var subDetailDescription: String,
        @SerializedName("subname") var subTitle: String,
        @SerializedName("subnum") var subNumber: Int //반복 일련번호 (순서)
        )


data class TravelIntro(
        @SerializedName("contentid") var contentId: Int,
        @SerializedName("contenttypeid") var contentTypeId: Int,
        @SerializedName("distance") var distance: String,
        @SerializedName("infocentertourcourse") var informationCenter: String,
        @SerializedName("schedule") var schedule: String,
        @SerializedName("taketime") var courseTime: String, //코스 총 소요시간
        @SerializedName("theme") var theme: String //코스 테마
)

data class AreaTour (
        @SerializedName("addr1") var fullAddress: String,// 서울시 종로구 평창30길 28
        @SerializedName("addr2") var areaAddress: String, //문화일경우 (평창동)
        @SerializedName("areacode") var areaCode: String,
        @SerializedName("cat1") var largeCategoryCode: String,
        @SerializedName("cat2") var mediumCategoryCode: String,
        @SerializedName("cat3") var smallCategoryCode: String,
        @SerializedName("contentid") var contentId: Int,
        @SerializedName("contenttypeid") var contentTypeId: Int, //관광타입
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
        @SerializedName("zipcode") var zipCode: String, //우편 번호
        @SerializedName("overview") var overview: String //설명

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
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(if (fullAddress == null) "" else fullAddress)
        writeString(if (areaAddress == null) "" else areaAddress)
        writeString(if (areaCode == null) "" else areaCode)
        writeString(if (largeCategoryCode == null) "" else largeCategoryCode)
        writeString(if (mediumCategoryCode == null) "" else mediumCategoryCode)
        writeString(if (smallCategoryCode == null) "" else smallCategoryCode)
        writeInt(contentId)
        writeInt(contentTypeId)
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
        writeString(if (overview == null) "" else overview)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AreaTour> = object : Parcelable.Creator<AreaTour> {
            override fun createFromParcel(source: Parcel): AreaTour = AreaTour(source)
            override fun newArray(size: Int): Array<AreaTour?> = arrayOfNulls(size)
        }
    }
}