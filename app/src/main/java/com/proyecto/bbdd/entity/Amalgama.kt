package com.proyecto.bbdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Amalgama",
    foreignKeys = [
        ForeignKey(
            entity = Poderes::class,
            parentColumns = ["id"],
            childColumns = ["fkvas_poder"],
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
        entity = DisciplinasClan::class,
        parentColumns = ["id"],
        childColumns = ["fkvas_disciplina_principal"],
        onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = DisciplinasClan::class,
            parentColumns = ["id"],
            childColumns = ["fkvas_disciplina_secundaria"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Poderes::class,
            parentColumns = ["id"],
            childColumns = ["requisito_poder"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    )

data class Amalgama(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val fkvas_poder: Int,
    val fkvas_disciplina_principal: Int,
    val fkvas_disciplina_secundaria: Int? = null,
    val nivel_disciplina_principal: Int,
    val nivel_disciplina_secundaria: Int? = null,
    val requisito_poder: Int? = null
)