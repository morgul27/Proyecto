package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.proyecto.bbdd.entity.Vastago

@Dao
interface VastagoDao {

    //lo que esta entre parentesis funciona como un metodo update
    //(onConflict = OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVastago(vastago: Vastago): Long

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

    //ver que poderes puede desbloquear el vastago
    @Query("""
        SELECT DISTINCT p.nombre AS poder
        FROM Vastago v
        INNER JOIN DisciplinasVas dv_principal ON dv_principal.fk_vas = v.id
        LEFT JOIN DisciplinasVas dv_secundaria ON dv_secundaria.fk_vas = v.id
        LEFT JOIN Amalgama a ON (
                a.fkvas_disciplina_principal = dv_principal.idDisciplinasVas AND 
                dv_principal.nivel >= a.nivel_disciplina_principal AND
                (a.fkvas_disciplina_secundaria IS NULL OR
                (a.fkvas_disciplina_secundaria = dv_secundaria.idDisciplinasVas AND 
                dv_secundaria.nivel >= a.nivel_disciplina_secundaria))
        )
        INNER JOIN Poderes p ON p.id = a.fkvas_poder
        WHERE v.id = :vastagoId
        ORDER BY p.id
    """)
    suspend fun getPoderes(vastagoId: Int): List<String>


    //ver las disciplina que le toca al vastago
    @Query("""
        SELECT dc.nombre 
        FROM DisciplinasClan dc
        INNER JOIN NNClanDisciplinas nnd ON dc.id = nnd.fk_disc
        INNER JOIN Clan c ON nnd.fk_clan = c.id
        INNER JOIN Vastago v ON v.fkvas_clan = c.id
        WHERE v.id = :vastagoId
    """)
    suspend fun getDisciplinasClanDeVas(vastagoId: Int): List<String>


    //ver las disciplina que le toca por clan
    @Query("""
        SELECT DISTINCT dc.nombre 
        FROM DisciplinasClan dc
        INNER JOIN NNClanDisciplinas nnd ON dc.id = nnd.fk_disc
        INNER JOIN Clan c ON nnd.fk_clan = c.id
        WHERE c.id = :vastagoClan
    """)
    suspend fun getDisciplinasPorClan(vastagoClan: Int): List<String>

    @Query("""
        SELECT DISTINCT dc.id
        FROM DisciplinasClan dc
        INNER JOIN NNClanDisciplinas nnd ON dc.id = nnd.fk_disc
        INNER JOIN Clan c ON nnd.fk_clan = c.id
        WHERE c.id = :vastagoClan
    """)
    suspend fun getIdDisciplinasPorClan(vastagoClan: Int): List<Int>


    //coger la id del ultimo vastago creado
    @Query("""SELECT id FROM Vastago ORDER BY id DESC LIMIT 1""")
    suspend fun getUltIdVas(): Int
}