package com.proyecto.bbdd.repository

import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.entity.Vastago
import com.proyecto.bbdd.interfaces.VastagoDao


class VastagoRepository(
    private val vastagoDao: VastagoDao
) {
    suspend fun insertVastago(vastago: Vastago) {
        vastagoDao.insertVastago(vastago)
    }

    suspend fun getVastagos(): List<Vastago> {
        return vastagoDao.getVastagos()
    }

    suspend fun getVastagosPorFk(idUsu: Int): List<Vastago> {
        return vastagoDao.getVastagosPorFk(idUsu)
    }

    suspend fun eliminarVastago(id: Int){
        vastagoDao.eliminarVastagoPorId(id)
    }

    suspend fun eliminarVastagoPorFk(fkvas_usu: Int){
        vastagoDao.eliminarVastagoPorFkvasUsu(fkvas_usu)
    }

    //eliminar si no hace falta
    suspend fun actualizarVastago(vastago: Vastago) {
        vastagoDao.actualizarVastago(vastago)
    }

    suspend fun getPoderes(vastagoId: Int): List<String> {
        return vastagoDao.getPoderes(vastagoId)
    }
}