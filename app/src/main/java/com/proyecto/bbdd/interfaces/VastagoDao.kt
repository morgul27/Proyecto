package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.entity.Vastago

@Dao
interface VastagoDao {

    //lo que esta entre parentesis funciona como un metodo update
    //(onConflict = OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVastago(vastago: Vastago)

    @Query("SELECT * FROM Vastago")
    suspend fun getVastagos(): List<Vastago>

    @Query("SELECT * FROM Vastago WHERE fkvas_usu = :fkvas_usu")
    suspend fun getVastagosPorFk(fkvas_usu: Int): List<Vastago>

    @Query("DELETE FROM Vastago WHERE id = :id")
    suspend fun eliminarVastagoPorId(id: Int)

    @Query("DELETE FROM Vastago WHERE fkvas_usu = :fkvas_usu")
    suspend fun eliminarVastagoPorFkvasUsu(fkvas_usu: Int)

    //eliminar si no hace falta
    @Query("SELECT * FROM Vastago WHERE id = :id")
    suspend fun getVastagoPorId(id: Int): Vastago?

    @Update
    suspend fun actualizarVastago(vastago: Vastago)

}