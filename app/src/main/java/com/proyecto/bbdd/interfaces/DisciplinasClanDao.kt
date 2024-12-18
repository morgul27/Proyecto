package com.proyecto.bbdd.interfaces

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.proyecto.bbdd.entity.DisciplinasClan

interface DisciplinasClanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisciplinasClan(disciplinasClan: DisciplinasClan)
}