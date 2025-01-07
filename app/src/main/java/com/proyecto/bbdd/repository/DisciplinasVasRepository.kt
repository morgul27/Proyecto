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

    suspend fun obtenerIdDisciplina(idDisciplina: Int, fkVas: Int): Int{
        return disciplinasVasDao.obteneridDisciplina(idDisciplina, fkVas)
    }


    suspend fun actualizarDisciplinasVas(disciplinasVas: DisciplinasVas) {
        disciplinasVasDao.actDisciplinasVas(disciplinasVas)
    }

}