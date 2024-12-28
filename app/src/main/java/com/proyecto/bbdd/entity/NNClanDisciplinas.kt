package com.proyecto.bbdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "NNClanDisciplinas",
    foreignKeys = [
        ForeignKey(
            entity = Clan::class,
            parentColumns = ["id"],
            childColumns = ["fk_clan"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DisciplinasClan::class,
            parentColumns = ["id"],
            childColumns = ["fk_disc"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
)
data class NNClanDisciplinas (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val fk_clan: Int,
    val fk_disc: Int
)