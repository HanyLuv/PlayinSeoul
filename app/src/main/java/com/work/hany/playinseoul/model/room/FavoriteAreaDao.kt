package com.work.hany.playinseoul.model.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface FavoriteAreaDao {

    @Query("SELECT * from favoriteArea")
    fun getAll(): List<FavoriteAreaData>

    @Insert(onConflict = REPLACE)
    fun insert(favoriteArea: FavoriteAreaData)

    @Query("DELETE from favoriteArea")
    fun deleteAll()
}