package com.work.hany.playinseoul.toursearch.vo

import com.work.hany.playinseoul.network.Area

data class SearchItem(var itemType: SearchItemType, var codes: ArrayList<Area>) {

    enum class SearchItemType(name: String ,code: Int) {
//            SERVICE("관광지상세",0), //서비스 분류(대/중/소)
            TOUR("관광지",1), //관광타입 뭐라고 정정하냐 ?
            AREA("지역",2) //지역
    }
}