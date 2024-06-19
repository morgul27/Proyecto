package com.proyecto

import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.entity.Vastago

data class MPState(
    var name: String,
    val names: List<Usuario> = emptyList(),
    val nombresVas: List<Vastago> = emptyList(),
    val nombreClanes: List<Clan> = emptyList(),
    val isLoading: Boolean = false,

    //Creacion de Vastago
    val id: Int? = null,
    var nombreVas: String ? = null,
    var clanVas: String ? = null,
    var experiencia: Int? = null,
    var generacion: Int? = null,
    var fuerza: Int? = null,
    var destreza: Int? = null,
    var resistencia: Int? = null,
    var carisma: Int? = null,
    var manipulacion: Int? = null,
    var compostura: Int? = null,
    var inteligencia: Int? = null,
    var astucia: Int? = null,
    var resolucion: Int? = null,
    var salud: Int? = null,
    var fuerza_voluntad: Int? = null,
    var fkvas_usu: Int? = 1,
    var fkvas_clan: Int? = 1,

    )
