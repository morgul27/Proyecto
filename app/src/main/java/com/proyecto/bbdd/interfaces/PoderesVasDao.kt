package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.proyecto.bbdd.entity.Poderes
import com.proyecto.bbdd.entity.PoderesVas

@Dao
interface PoderesVasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoderesVas(poderesVas: PoderesVas)
}