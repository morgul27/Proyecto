package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proyecto.bbdd.entity.DisciplinasVas

@Dao
interface DisciplinasVasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisciplinasVas(disciplinasVas: DisciplinasVas)


    @Query("SELECT dv.id FROM DisciplinasVas dv WHERE fkDisciplinasVas = :idDisciplina and fk_vas = :fkVas")
    suspend fun obteneridDisciplina(idDisciplina: Int, fkVas: Int): Int
}