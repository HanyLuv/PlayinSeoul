package com.work.hany.playinseoul.model.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "favoriteArea")
data class FavoriteAreaData (@PrimaryKey(autoGenerate = true) var id: Long?,
                             @ColumnInfo(name = "areaName") var areaName: String,
                             @ColumnInfo(name = "areaCode") var areaCode: Int,
                             @ColumnInfo(name = "favoriteTours") var favoriteTours: ArrayList<FavoriteTour>
)

data class FavoriteTour(var contentTypeId: String,
                        var contentId: String,
                        var contentTitle: String,
                        var modifiedTime: Long, //개시 날짜
                        var contentImageUri: String,
                        var isFavorite: Boolean,
                        var contentAddr: String
)