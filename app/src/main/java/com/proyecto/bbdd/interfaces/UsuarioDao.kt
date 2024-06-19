package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proyecto.bbdd.entity.Usuario

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsuario(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    suspend fun getUsuarios(): List<Usuario>

    @Query("DELETE FROM Usuario WHERE id = :id")
    suspend fun eliminarUsuarioPorId(id: Int)

    @Query("SELECT id FROM Usuario WHERE name = :nombre")
    suspend fun getIdPorNombreUsu(nombre: String): Int

    //borrar si no lo uso
    @Query("SELECT * FROM Usuario WHERE id = :id")
    suspend fun obtenerUsuarioPorId(id: Int): Usuario?

    @Query("DELETE FROM Usuario")
    suspend fun deleteAllUsuarios()
}