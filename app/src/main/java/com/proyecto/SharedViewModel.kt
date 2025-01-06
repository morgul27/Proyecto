package com.proyecto

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.bbdd.entity.DisciplinasVas
import com.proyecto.bbdd.entity.Vastago
import com.proyecto.bbdd.repository.ClanRepository
import com.proyecto.bbdd.repository.DisciplinasVasRepository
import com.proyecto.bbdd.repository.VastagoRepository
import kotlinx.coroutines.launch

class SharedViewModel(
    private  val disciplinasVasRepository : DisciplinasVasRepository,
    private  val vasRepository: VastagoRepository,
) : ViewModel() {
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
    val fkUsuario = mutableStateOf<Int?>(null)
    val fkClan = mutableStateOf<Int?>(null)
    //habilidades
    //columna 1
    var armas_de_fuego = mutableStateOf<Int?>(null)
    var artesania = mutableStateOf<Int?>(null)
    var atletismo = mutableStateOf<Int?>(null)
    var conducir = mutableStateOf<Int?>(null)
    var latrocinio = mutableStateOf<Int?>(null)
    var pelea = mutableStateOf<Int?>(null)
    var pelea_con_armas = mutableStateOf<Int?>(null)
    var sigilo = mutableStateOf<Int?>(null)
    var superviviencia = mutableStateOf<Int?>(null)
    //columna 2
    var callejeo = mutableStateOf<Int?>(null)
    var etiqueta = mutableStateOf<Int?>(null)
    var interpretacion = mutableStateOf<Int?>(null)
    var intimidacion = mutableStateOf<Int?>(null)
    var liderazgo = mutableStateOf<Int?>(null)
    var perspicacia = mutableStateOf<Int?>(null)
    var persuasion = mutableStateOf<Int?>(null)
    var subterfugio = mutableStateOf<Int?>(null)
    var trato_con_animales = mutableStateOf<Int?>(null)
    //columna 3
    var academicismo = mutableStateOf<Int?>(null)
    var ciencias = mutableStateOf<Int?>(null)
    var consciencia = mutableStateOf<Int?>(null)
    var finanzas = mutableStateOf<Int?>(null)
    var investigacion = mutableStateOf<Int?>(null)
    var medicina = mutableStateOf<Int?>(null)
    var ocultismo = mutableStateOf<Int?>(null)
    var politica = mutableStateOf<Int?>(null)
    var tecnologia = mutableStateOf<Int?>(null)

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

    //guardar en la mejoras de personajes
    fun guardarVastagoConDisciplinas(puntos: List<Int>, listaIdDisciplinas: List<Int>, onComplete: () -> Unit) {
        viewModelScope.launch {
            // 1. Guardar el vástago y obtener su ID
            val vastagoId: Long = vasRepository.insertVastago(
                Vastago(
                    nombreVas = (vasName ?: "Cain").toString(),
                    clan = (vasClan ?: "Nosferatu").toString(),
                    experiencia = vasExp.value ?: 0,
                    generacion = vasGeneracion.value ?: 0,
                    // Atributos
                    fuerza = vasFuerza.value ?: 0,
                    destreza = vasDestr.value ?: 0,
                    resistencia = vasResist.value ?: 0,
                    carisma = vasCarisma.value ?: 0,
                    manipulacion = vasMan.value ?: 0,
                    compostura = vasCompostura.value ?: 0,
                    inteligencia = vasIntel.value ?: 0,
                    astucia = vasAstucia.value ?: 0,
                    resolucion = vasResolucion.value ?: 0,
                    salud = vasSalud.value ?: 0,
                    fuerza_voluntad = vasVoluntad.value ?: 0,
                    fkvas_usu = fkUsuario.value ?: 1,
                    fkvas_clan = fkClan.value ?: 1,
                    // Habilidades
                    armas_de_fuego = armas_de_fuego.value ?: 0,
                    artesania = artesania.value ?: 0,
                    atletismo = atletismo.value ?: 0,
                    conducir = conducir.value ?: 0,
                    pelea = pelea.value ?: 0,
                    pelea_con_armas = pelea_con_armas.value ?: 0,
                    superviviencia = superviviencia.value ?: 0,
                    callejeo = callejeo.value ?: 0,
                    etiqueta = etiqueta.value ?: 0,
                    interpretacion = interpretacion.value ?: 0,
                    liderazgo = liderazgo.value ?: 0,
                    perspicacia = perspicacia.value ?: 0,
                    persuasion = persuasion.value ?: 0,
                    subterfugio = subterfugio.value ?: 0,
                    trato_con_animales = trato_con_animales.value ?: 0,
                    academicismo = academicismo.value ?: 0,
                    ciencias = ciencias.value ?: 0,
                    consciencia = consciencia.value ?: 0,
                    finanzas = finanzas.value ?: 0,
                    investigacion = investigacion.value ?: 0,
                    medicina = medicina.value ?: 0,
                    ocultismo = ocultismo.value ?: 0,
                    politica = politica.value ?: 0,
                    tecnologia = tecnologia.value ?: 0
                )
            ) // El valor 'vastagoId' es de tipo Long

            // 3. Guardar las disciplinas asociadas al vástago recién insertado
            listaIdDisciplinas.forEachIndexed { index, disciplinaId ->
                vasId.value?.let {
                    DisciplinasVas(
                        fkDisciplinasVas = disciplinaId,
                        nivel = puntos[index],
                        fk_vas = it
                    )
                }?.let {
                    disciplinasVasRepository.saveDisciplinasVas(
                        it
                    )
                }
            }

            // 4. Llamar a la función final cuando _todo esté completo
            onComplete()
        }
    }
}
