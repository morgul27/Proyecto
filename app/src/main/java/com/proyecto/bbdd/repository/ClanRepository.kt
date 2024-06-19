package com.proyecto.bbdd.repository

import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.interfaces.ClanDao

class ClanRepository(
    private val clanDao: ClanDao
) {
    suspend fun insertarClan(clan: Clan) {
        clanDao.insertClan(clan)
    }

    suspend fun obtenerClan(): List<Clan> {
        return clanDao.getClan()
    }

    suspend fun obtenerClanPorId(id: Int): Clan? {
        return clanDao.obtenerClanPorId(id)
    }

    suspend fun eliminarClanPorId(id: Int) {
        clanDao.eliminarClanPorId(id)
    }

    suspend fun getClanes(): List<Clan> {
        val entities = clanDao.getClan()
        return entities.map {
            Clan(nombreClan = it.nombreClan)
        }
    }

    suspend fun obtenerIdPorNombreClan(nombreVas : String): Int {
        return clanDao.getIdPorNombreClan(nombreVas)
    }
}