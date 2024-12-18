package com.proyecto.bbdd.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Poderes (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nombre: String
)