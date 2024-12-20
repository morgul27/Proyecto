package com.proyecto.bbdd.repository

import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.entity.Poderes
import com.proyecto.bbdd.interfaces.PoderesDao
import kotlinx.coroutines.flow.Flow

class PoderesRepository (
    private val poderesDao: PoderesDao
){
//    fun obtenerPoderesFiltrados(requisito: Int): Flow<List<Poderes>> {
//        return poderesDao.obtenerPoderesFiltrados(requisito)
//    }
}