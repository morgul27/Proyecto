package com.proyecto.bbdd.interfaces

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.proyecto.bbdd.entity.NNClanDisciplinas

interface NNClanDisciplinasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNNClanDisciplinas(nnClanDisciplinas: NNClanDisciplinas)
}