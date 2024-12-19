package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.proyecto.bbdd.entity.Poderes

@Dao
interface PoderesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoderes(poderes: Poderes)
}