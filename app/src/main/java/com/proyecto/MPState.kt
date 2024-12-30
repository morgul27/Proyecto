package com.proyecto

import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.entity.DisciplinasVas
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.entity.Vastago

data class MPState(
    var name: String,
    //listas
    val names: List<Usuario> = emptyList(),
    val nombresVas: List<Vastago> = emptyList(),
    val nombreClanes: List<Clan> = emptyList(),
    val listaDisciplinasPorVas: List<String> = emptyList(),
    val listaDisciplinasPorClan: List<String> = emptyList(),
    val listaPoderes: List<String> = emptyList(),
    val isLoading: Boolean = false,

    //Creacion de Vastago
    val id: Int? = null,
    var nombreVas: String? = null,
    var clanVas: String? = null,
    var experiencia: Int? = null,
    var generacion: Int? = null,
    //Atributos
    var fuerza: Int = 0,
    var destreza: Int = 0,
    var resistencia: Int = 0,
    var carisma: Int = 0,
    var manipulacion: Int = 0,
    var compostura: Int = 0,
    var inteligencia: Int = 0,
    var astucia: Int = 0,
    var resolucion: Int = 0,
    var salud: Int = 0,
    var fuerza_voluntad: Int = 0,
    //habilidades
    //columna 1
    var armas_de_fuego: Int? = 0,
    var artesania: Int? = 0,
    var atletismo: Int? = 0,
    var conducir: Int? = 0,
    var latrocinio: Int? = 0,
    var pelea: Int? = 0,
    var pelea_con_armas: Int = 0,
    var sigilo: Int = 0,
    var superviviencia: Int? = 0,
    //columna 2
    var callejeo: Int? = 0,
    var etiqueta: Int? = 0,
    var interpretacion: Int? = 0,
    var intimidacion: Int? = 0,
    var liderazgo: Int? = 0,
    var perspicacia: Int? = 0,
    var persuasion: Int? = 0,
    var subterfugio: Int? = 0,
    var trato_con_animales: Int? = 0,
    //columna 3
    var academicismo: Int? = 0,
    var ciencias: Int? = 0,
    var consciencia: Int? = 0,
    var finanzas: Int? = 0,
    var investigacion: Int? = 0,
    var medicina: Int? = 0,
    var ocultismo: Int? = 0,
    var politica: Int? = 0,
    var tecnologia: Int? = 0,
    //FKs
    var fkvas_usu: Int? = 1,
    var fkvas_clan: Int? = 1,


    )
