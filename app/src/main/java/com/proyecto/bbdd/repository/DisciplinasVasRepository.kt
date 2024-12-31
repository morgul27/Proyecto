package com.proyecto.bbdd.repository

import com.proyecto.bbdd.entity.DisciplinasVas
import com.proyecto.bbdd.entity.Vastago
import com.proyecto.bbdd.interfaces.DisciplinasVasDao

class DisciplinasVasRepository (
    private val disciplinasVasDao: DisciplinasVasDao
){
    suspend fun saveDisciplinasVas(disciplinasVas: DisciplinasVas) {
        disciplinasVasDao.insertDisciplinasVas(disciplinasVas)
    }
}