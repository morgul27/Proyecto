package com.proyecto.bbdd.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.proyecto.bbdd.entity.PoderesVas
import com.proyecto.bbdd.interfaces.PoderesVasDao

class PoderesVasRepository (
    private val poderesVasDao: PoderesVasDao
){
    suspend fun insertPoderesVas(poderesVas: PoderesVas) {
        return poderesVasDao.insertPoderesVas(poderesVas)
    }

    // Función para actualizar un poder existente
    suspend fun actualizarPoder(poder: PoderesVas) {
        poderesVasDao.actualizarPoder(poder)
    }

    // Función para obtener un poder existente basado en fk_disciplinas y nombre
    suspend fun obtenerPoder(fkDisciplinas: Int, nombre: String): PoderesVas? {
        return poderesVasDao.obtenerPoder(fkDisciplinas, nombre)
    }

}