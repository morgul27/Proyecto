package com.proyecto.bbdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "DisciplinasVas",
    foreignKeys = [
        ForeignKey(
            entity = Vastago::class,
            parentColumns = ["id"],
            childColumns = ["fk_vas"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class DisciplinasVas (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val idDisciplinasVas: Int? = null,
    val nivel: Int? = null,
    val fk_vas: Int
)