package com.proyecto.bbdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Vastago",
    foreignKeys = [ForeignKey(
        entity = Usuario::class,
        parentColumns = ["id"],
        childColumns = ["fkvas_usu"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Clan::class,
            parentColumns = ["id"],
            childColumns = ["fkvas_clan"],
            onDelete = ForeignKey.CASCADE
        )
    ],

)
data class Vastago(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nombreVas: String,
    val clan: String,
    var experiencia: Int,
    var generacion: Int,
    var fuerza: Int,
    var destreza: Int,
    var resistencia: Int,
    var carisma: Int,
    var manipulacion: Int,
    var compostura: Int,
    var inteligencia: Int,
    var astucia: Int,
    var resolucion: Int,
    var salud: Int,
    var fuerza_voluntad: Int,
    val fkvas_usu: Int, // Clave foránea que hace referencia al ID del Usuario
    val fkvas_clan: Int// Clave foránea que hace referencia al ID del Clan
)
