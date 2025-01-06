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
    //Atributos
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
    //Habilidades
    //columna 1
    var armas_de_fuego: Int = 0,
    var artesania: Int = 0,
    var atletismo: Int = 0,
    var conducir: Int = 0,
    var latrocinio: Int = 0,
    var pelea: Int = 0,
    var pelea_con_armas: Int = 0,
    var sigilo: Int = 0,
    var superviviencia: Int = 0,
    //columna 2
    var callejeo: Int = 0,
    var etiqueta: Int = 0,
    var interpretacion: Int = 0,
    var intimidacion: Int = 0,
    var liderazgo: Int = 0,
    var perspicacia: Int = 0,
    var persuasion: Int = 0,
    var subterfugio: Int = 0,
    var trato_con_animales: Int = 0,
    //columna 3
    var academicismo: Int = 0,
    var ciencias: Int = 0,
    var consciencia: Int = 0,
    var finanzas: Int = 0,
    var investigacion: Int = 0,
    var medicina: Int = 0,
    var ocultismo: Int = 0,
    var politica: Int = 0,
    var tecnologia: Int = 0,
    //FKs
    val fkvas_usu: Int, // Clave foránea que hace referencia al ID del Usuario
    val fkvas_clan: Int// Clave foránea que hace referencia al ID del Clan
)
