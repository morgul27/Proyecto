package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.proyecto.bbdd.entity.Amalgama

@Dao
interface AmalgamaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAmalgama(amalgama: Amalgama)
}