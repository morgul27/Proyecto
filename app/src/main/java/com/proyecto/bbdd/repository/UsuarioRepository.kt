package com.proyecto.bbdd.repository

import android.annotation.SuppressLint
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.interfaces.UsuarioDao

class UsuarioRepository(
    private val usuarioDao: UsuarioDao
) {
    suspend fun getUsuario(): List<Usuario>{
        val entities = usuarioDao.getUsuarios()
        return entities.map {
            Usuario(name = it.name, id = it.id)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun insertUsuario(usuario: Usuario){
        val entity = Usuario(name = usuario.name)
            usuarioDao.insertUsuario(entity)
    }

    suspend fun obtenerUsuarioPorId(id: Int): Usuario? {
        val usuarioEntity = usuarioDao.obtenerUsuarioPorId(id)
        return usuarioEntity?.let { Usuario(it.id, it.name) }
    }

    suspend fun eliminarUsuarioPorId(id: Int){
        usuarioDao.eliminarUsuarioPorId(id)
    }

    suspend fun obtenerIdPorNombreUsu(nombre : String): Int {
        return usuarioDao.getIdPorNombreUsu(nombre)
    }
}