package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.entity.Usuario

@Dao
interface ClanDao {

    //lo que esta entre parentesis funciona como un metodo update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClan(clan: Clan)

    @Query("SELECT * FROM Clan")
    suspend fun getClan(): List<Clan>

    @Query("SELECT * FROM Clan WHERE id = :id")
    suspend fun obtenerClanPorId(id: Int): Clan?

    @Query("DELETE FROM Clan WHERE id = :id")
    suspend fun eliminarClanPorId(id: Int)

    @Query("SELECT id FROM Clan WHERE nombreClan = :clanVas")
    suspend fun getIdPorNombreClan(clanVas: String): Int

    //borrar si al final no lo uso
    @Query("SELECT id FROM Clan WHERE nombreClan = :clanVas")
    suspend fun UpdateVastago(clanVas: String): Int
}