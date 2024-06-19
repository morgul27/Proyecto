package com.proyecto

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val vasId = mutableStateOf<Int?>(null)
    val userName = mutableStateOf<String?>(null)
    var userId: Int? = null
    val vasName = mutableStateOf<String?>(null)
    val vasClan = mutableStateOf<String?>(null)
    val vasGeneracion = mutableStateOf<Int?>(null)
    val vasFuerza = mutableStateOf<Int?>(null)
    val vasDestr = mutableStateOf<Int?>(null)
    val vasResist = mutableStateOf<Int?>(null)
    val vasCarisma = mutableStateOf<Int?>(null)
    val vasMan = mutableStateOf<Int?>(null)
    val vasCompostura = mutableStateOf<Int?>(null)
    val vasIntel = mutableStateOf<Int?>(null)
    val vasAstucia = mutableStateOf<Int?>(null)
    val vasResolucion = mutableStateOf<Int?>(null)
    val vasSalud = mutableStateOf<Int?>(null)
    val vasVoluntad = mutableStateOf<Int?>(null)
    val vasExp = mutableStateOf<Int?>(null)
    val fkClan = mutableStateOf<Int?>(null)

    fun setUserName(name: String) {
        userName.value = name
    }
    fun setId(id: Int) {
        userId = id
    }

    fun getUserName(): String? {
        return userName.value
    }

    val listaVastagos = mutableStateOf<String?>(null)

    fun setVastagoInfo(
        id: Int,
        name: String,
        clan: String,
        generacion: Int,
        fuerza: Int,
        destreza: Int,
        resistencia: Int,
        carisma: Int,
        manipulacion: Int,
        compostura: Int,
        inteligencia: Int,
        astucia: Int,
        resolucion: Int,
        salud: Int,
        voluntad: Int,
        experiencia: Int,
        fkvasClan: Int
    ) {
        vasId.value = id
        vasName.value = name
        vasClan.value = clan
        vasGeneracion.value = generacion
        vasFuerza.value = fuerza
        vasDestr.value = destreza
        vasResist.value = resistencia
        vasCarisma.value = carisma
        vasMan.value = manipulacion
        vasCompostura.value = compostura
        vasIntel.value = inteligencia
        vasAstucia.value = astucia
        vasResolucion.value = resolucion
        vasSalud.value = salud
        vasVoluntad.value = voluntad
        vasExp.value = experiencia
        fkClan.value = fkvasClan

    }


    fun getVastagoNombre(): String? {
        return listaVastagos.value
    }
}
