package com.proyecto

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val vasGeneracion = mutableStateOf<Int>(0)
    val vasFuerza = mutableStateOf<Int>(0)
    val vasDestr = mutableStateOf<Int>(0)
    val vasResist = mutableStateOf<Int>(0)
    val vasCarisma = mutableStateOf<Int>(0)
    val vasMan = mutableStateOf<Int>(0)
    val vasCompostura = mutableStateOf<Int>(0)
    val vasIntel = mutableStateOf<Int>(0)
    val vasAstucia = mutableStateOf<Int>(0)
    val vasResolucion = mutableStateOf<Int>(0)
    val vasSalud = mutableStateOf<Int>(0)
    val vasVoluntad = mutableStateOf<Int>(0)
    val vasExp = mutableStateOf<Int>(0)
    //habilidades
    //columna 1
    var armas_de_fuego = mutableStateOf<Int>(0)
    var artesania = mutableStateOf<Int>(0)
    var atletismo = mutableStateOf<Int>(0)
    var conducir = mutableStateOf<Int>(0)
    var latrocinio = mutableStateOf<Int>(0)
    var pelea = mutableStateOf<Int>(0)
    var pelea_con_armas = mutableStateOf<Int>(0)
    var sigilo = mutableStateOf<Int>(0)
    var superviviencia = mutableStateOf<Int>(0)
    //columna 2
    var callejeo = mutableStateOf<Int>(0)
    var etiqueta = mutableStateOf<Int>(0)
    var interpretacion = mutableStateOf<Int>(0)
    var intimidacion = mutableStateOf<Int>(0)
    var liderazgo = mutableStateOf<Int>(0)
    var perspicacia = mutableStateOf<Int>(0)
    var persuasion = mutableStateOf<Int>(0)
    var subterfugio = mutableStateOf<Int>(0)
    var trato_con_animales = mutableStateOf<Int>(0)
    //columna 3
    var academicismo = mutableStateOf<Int>(0)
    var ciencias = mutableStateOf<Int>(0)
    var consciencia = mutableStateOf<Int>(0)
    var finanzas = mutableStateOf<Int>(0)
    var investigacion = mutableStateOf<Int>(0)
    var medicina = mutableStateOf<Int>(0)
    var ocultismo = mutableStateOf<Int>(0)
    var politica = mutableStateOf<Int>(0)
    var tecnologia = mutableStateOf<Int>(0)
    //FKs
    var fkUsu = mutableStateOf<Int>(0)
    var fkClan = mutableStateOf<Int>(0)

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
        //Atributos
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
        //Habilidades
        //columna 1
        armas_de_fuego: Int,
        artesania: Int,
        atletismo: Int,
        conducir: Int,
        latrocinio: Int,
        pelea: Int,
        pelea_con_armas: Int,
        sigilo: Int,
        superviviencia: Int,
        //columna 2
        callejeo: Int,
        etiqueta: Int,
        interpretacion: Int,
        intimidacion: Int,
        liderazgo: Int,
        perspicacia: Int,
        persuasion: Int,
        subterfugio: Int,
        trato_con_animales: Int,
        //columna 3
        academicismo: Int,
        ciencias: Int,
        consciencia: Int,
        finanzas: Int,
        investigacion: Int,
        medicina: Int,
        ocultismo: Int,
        politica: Int,
        tecnologia: Int,
        //FKs
        fkUsuario: Int,
        fkvasClan: Int
    ) {
        vasId.value = id
        vasName.value = name
        vasClan.value = clan
        vasGeneracion.value = generacion
        //Atributos
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
        //Habilidades
        //columna 1
        this.armas_de_fuego.value = armas_de_fuego
        this.artesania.value = artesania
        this.atletismo.value = atletismo
        this.conducir.value = conducir
        this.latrocinio.value = latrocinio
        this.pelea.value = pelea
        this.pelea_con_armas.value = pelea_con_armas
        this.sigilo.value = sigilo
        this.superviviencia.value = superviviencia
        //columna 2
        this.callejeo.value = callejeo
        this.etiqueta.value = etiqueta
        this.interpretacion.value = interpretacion
        this.intimidacion.value = intimidacion
        this.liderazgo.value = liderazgo
        this.perspicacia.value = perspicacia
        this.persuasion.value = persuasion
        this.subterfugio.value = subterfugio
        this.trato_con_animales.value = trato_con_animales
        //columna 3
        this.academicismo.value =academicismo
        this.ciencias.value = ciencias
        this.consciencia.value = consciencia
        this.finanzas.value = finanzas
        this.investigacion.value = investigacion
        this.medicina.value = medicina
        this.ocultismo.value = ocultismo
        this.politica.value = politica
        this.tecnologia.value = tecnologia
        //FKs
        fkUsu.value = fkUsuario
        fkClan.value = fkvasClan

    }


    fun getVastagoNombre(): String? {
        return listaVastagos.value
    }

    //guardar en la mejoras de personajes
    fun ActualizarVastagoConDisciplinasSH(puntos: List<Int>, listaIdDisciplinas: List<Int>, viewModel: MPViewModel, onComplete: () -> Unit) {
        viewModelScope.launch {
            // 1. Guardar el vástago y obtener su ID
            vasRepository.actualizarVastago(
                Vastago(
                    id = vasId.value ?: 1,
                    nombreVas = (vasName.value ?: "Cain"),
                    clan = (vasClan.value ?: "Nosferatu"),
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
                    fkvas_usu = fkUsu.value ?: 1,
                    fkvas_clan = fkClan.value ?: 1,
                    // Habilidades
                    // columna 1
                    armas_de_fuego = armas_de_fuego.value ?: 0,
                    artesania = artesania.value ?: 0,
                    atletismo = atletismo.value ?: 0,
                    conducir = conducir.value ?: 0,
                    latrocinio = latrocinio.value ?: 0,
                    pelea = pelea.value ?: 0,
                    pelea_con_armas = pelea_con_armas.value ?: 0,
                    sigilo = sigilo.value ?: 0,
                    superviviencia = superviviencia.value ?: 0,
                    // columna 2
                    callejeo = callejeo.value ?: 0,
                    etiqueta = etiqueta.value ?: 0,
                    interpretacion = interpretacion.value ?: 0,
                    intimidacion = intimidacion.value ?: 0,
                    liderazgo = liderazgo.value ?: 0,
                    perspicacia = perspicacia.value ?: 0,
                    persuasion = persuasion.value ?: 0,
                    subterfugio = subterfugio.value ?: 0,
                    trato_con_animales = trato_con_animales.value ?: 0,
                    // columna 3
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
            )
            //solicitar su id

            listaIdDisciplinas.forEachIndexed { index, disciplinaId ->
                val id = viewModel.obteneridDisciplina(disciplinaId, vasId.value?: 1)
                disciplinasVasRepository.actualizarDisciplinasVas(
                    DisciplinasVas(
                        id = id,
                        fkDisciplinasVas = disciplinaId,
                        nivel = puntos[index],
                        fk_vas = vasId.value ?: 1
                    )
                )
            }

            // 4. Llamar a la función final cuando _todo esté completo
            onComplete()
        }
    }
}
