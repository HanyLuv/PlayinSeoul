package com.work.hany.playinseoul.util

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import java.text.SimpleDateFormat
import java.util.*

//fun Long.toDateString(): String{
//    val crateFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.getDefault())
//    crateFormat.timeZone = TimeZone.getTimeZone("UTC")
//
//    val dateFormat = SimpleDateFormat("yyMMddHHmmssZ", Locale.getDefault())
//    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
//    return crateFormat.format(dateFormat.parse(this.toString()))
//}


fun Long.toDateString(): String {
    var dateString = StringBuilder(this.toString()).insert(4, "년 ").insert(8, "월").insert(11, "일").toString();

    return  dateString.removeRange(12,dateString.length)
}

