package com.proyecto.bbdd.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Amalgama(
    @PrimaryKey(autoGenerate = true)

    val fkvas_poder: Int,
    val fkvas_disciplina: Int
)