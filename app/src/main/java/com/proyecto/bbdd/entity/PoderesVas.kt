package com.proyecto.bbdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "PoderesVas",
    foreignKeys = [
        ForeignKey(
            entity = DisciplinasVas::class,
            parentColumns = ["id"],
            childColumns = ["fk_disciplinas"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class PoderesVas (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nombre: String,
    val nivel: Int,
    val fk_disciplinas: Int
)