package com.work.hany.playinseoul.toursearch.vo

import com.work.hany.playinseoul.network.Area

/**
*
*
* 그냥 api areaCode <쏘았을때 서울, 경기, 부산
* api areaCode?areaCode=1 <쏘았을때 강남구, 강북구, 강서구
* api areaCode?areaCode=1&sigunguCode=1 < 매탄1동 매탄2동... 이런식
* **/
class SearchItem {
    internal var contentTypeId: String = ""
    internal var areaCode: String = "" //지역코드
    internal var cityCode: String = "" //시/구/군 코드, 지역코드 필수
    internal var cat1: String = "" //대분류
    internal var cat2: String = "" //중분류
    internal var cat3: String = "" // 소분류
    internal var keyword: String = "" //검색 단어



    fun selectedTag(type: SearchSection.SearchItemType, area: Area) {
        when (type) {
            SearchSection.SearchItemType.AREA ->
                if (area.depth == 0 ) {
                    areaCode = area.code
                } else {
                    cityCode = area.code
                }

            SearchSection.SearchItemType.TOUR -> contentTypeId = area.code
            SearchSection.SearchItemType.TOUR_LARGE -> cat1 = area.code
            SearchSection.SearchItemType.TOUR_MEDIUM -> cat2 = area.code
            SearchSection.SearchItemType.TOUR_SMALL -> cat3 = area.code
        }
    }


    fun deletedTag(type: SearchSection.SearchItemType, area: Area) {
        selectedTag(type, area)
    }
}