package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proyecto.bbdd.entity.PoderesVas

@Dao
interface PoderesVasDao {

    @Insert()
    suspend fun insertPoderesVas(poderesVas: PoderesVas)

    @Update
    suspend fun actualizarPoder(poder: PoderesVas)

    @Query("SELECT * FROM PoderesVas WHERE fk_disciplinas = :fkDisciplinas AND nombre = :nombre")
    suspend fun obtenerPoder(fkDisciplinas: Int, nombre: String): PoderesVas?
}