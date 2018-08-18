package com.work.hany.playinseoul.toursearch.vo

import com.work.hany.playinseoul.network.Area

data class SearchSection(var itemType: SearchItemType, var codes: ArrayList<Area>) {

    enum class SearchItemType(var tagName: String ,var code: Int) {
//            SERVICE("관광지상세",0), //서비스 분류(대/중/소)
            TOUR("관광지",1), //관광타입 뭐라고 정정하냐 ?
            TOUR_LARGE("대분류",2), //관광타입 뭐라고 정정하냐 ?
            TOUR_MEDIUM("중분류",3), //관광타입 뭐라고 정정하냐 ?
            TOUR_SMALL("소분류",4), //관광타입 뭐라고 정정하냐 ?
            AREA("지역",5), //지역
            CITY("시/구/군",6), //시구
            EMPTY("",7) //빈값

    }
}