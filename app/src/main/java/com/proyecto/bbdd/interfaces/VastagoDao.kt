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

    @Query("""
        SELECT p.nombre AS poder
        FROM Vastago v
        INNER JOIN DisciplinasVas dv_principal ON dv_principal.fk_vas = v.id
        INNER JOIN DisciplinasVas dv_secundaria ON dv_secundaria.fk_vas = v.id
        INNER JOIN Amalgama a ON (
            a.fkvas_disciplina_principal = dv_principal.fk_vas AND 
            dv_principal.nivel >= a.nivel_disciplina_principal AND
            a.fkvas_disciplina_secundaria = dv_secundaria.fk_vas AND 
            dv_secundaria.nivel >= a.nivel_disciplina_secundaria
        )
        INNER JOIN Poderes p ON p.id = a.fkvas_poder
        WHERE v.id = :vastagoId
    """)
    suspend fun getPoderes(vastagoId: Int): List<String>

}