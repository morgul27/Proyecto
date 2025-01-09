package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proyecto.bbdd.entity.DisciplinasVas
import com.proyecto.bbdd.entity.Vastago

@Dao
interface DisciplinasVasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisciplinasVas(disciplinasVas: DisciplinasVas)


    @Query("SELECT dv.id FROM DisciplinasVas dv WHERE fkDisciplinasVas = :idDisciplina and fk_vas = :fkVas")
    suspend fun obteneridDisciplina(idDisciplina: Int, fkVas: Int): Int

    @Update
    suspend fun actDisciplinasVas(disciplinasVas: DisciplinasVas)

    //obtener niveles
    @Query("SELECT dv.nivel FROM DisciplinasVas dv WHERE fk_vas = :fkVas")
    suspend fun obtenernivelesDisciplinas(fkVas: Int): List <Int>
}