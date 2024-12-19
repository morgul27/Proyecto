package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.proyecto.bbdd.entity.DisciplinasVas

@Dao
interface DisciplinasVasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisciplinasVas(disciplinasVas: DisciplinasVas)
}