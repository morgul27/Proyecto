package com.proyecto.bbdd.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proyecto.bbdd.entity.Poderes
import kotlinx.coroutines.flow.Flow

@Dao
interface PoderesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoderes(poderes: Poderes)

    //Esta Query es para sacar las habilidades que necesiten un requisito previo, mirar si me sirve
//    @Query("""
//    SELECT p.*
//    FROM Poderes p
//    INNER JOIN Amalgama a ON p.id = a.fkvas_poder
//    WHERE a.requisito_poder IS NULL OR a.requisito_poder IN (
//        SELECT requisito_poder
//        FROM Amalgama
//        WHERE requisito_poder = :requisito
//    )
//""")fun obtenerPoderesFiltrados(requisito: Int): Flow<List<Poderes>>

}