package com.proyecto

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.entity.Vastago
import com.proyecto.bbdd.repository.ClanRepository
import com.proyecto.bbdd.repository.UsuarioRepository
import com.proyecto.bbdd.repository.VastagoRepository
import kotlinx.coroutines.launch


class MPViewModel(
    private  val usuRepository: UsuarioRepository,
    private  val vasRepository: VastagoRepository,
    private  val clanRepository: ClanRepository,

) : ViewModel(){
    var state by mutableStateOf(MPState("", listOf(), nombreVas = "", clanVas = ""))
        private set

    val showSecondMenu = mutableStateOf(false)
    val showSecondMenuHist = mutableStateOf(false)
    val direccionHist = mutableStateOf(false)
    var perder by mutableStateOf(false)
    val direccionPDF = mutableStateOf(false)
    var nDados = 0 //dados que tiene el usuario de caracteristica
    var dificult = mutableListOf(1, 3, 6) //dificultad de tirada
    var movDif = 0 //mover la dificultad

    fun toggleSecondMenu() {
        showSecondMenu.value = !showSecondMenu.value
    }
    fun cambioMenuHist() {
        showSecondMenuHist.value = !showSecondMenuHist.value
    }

    fun cambiarND(dados: MutableState<Int?>){
        nDados = dados.value ?: 0
    }
    fun cambiarNDInt(dados:Int?){
        nDados = dados ?: 0
    }

    var menuMejoraP: Boolean = false


    // Propiedad para controlar el estado de carga
    var isLoading by mutableStateOf(false)
        private set


    //Funciones de Usuario
    fun obtenerUsuarios(){
        isLoading = true // Marcar como cargando
        viewModelScope.launch {
            state = state.copy(
                names = usuRepository.getUsuario()
            )
            isLoading = false // Marcar como no cargando después de obtener los usuarios
        }
    }

    fun onNameChange(name: String){
        state = state.copy(
            name = name
        )
    }

    fun saveUsuario(){
        val usuario = Usuario(
            name = state.name
        )
        viewModelScope.launch {
            usuRepository.insertUsuario(usuario)
        }
    }

    suspend fun obtenerIdPorNombreUsu(nombre: String): Int {
        var idUsu = -1 // Valor por defecto si no se encuentra ningún ID válido
        idUsu = usuRepository.obtenerIdPorNombreUsu(nombre)
        return idUsu
    }

    fun eliminarUsuarioPorId(id:Int) {
        viewModelScope.launch {
            vasRepository.eliminarVastagoPorFk(id)
            usuRepository.eliminarUsuarioPorId(id)
        }
    }




    //Funciones de Vastagos
    fun eliminarVasPorId(id:Int) {
        viewModelScope.launch {
            vasRepository.eliminarVastago(id)
        }
    }
    fun obtenerVastagos(){
        viewModelScope.launch {
            state = state.copy(
                nombresVas = vasRepository.getVastagos()
            )
        }
    }

    fun obtenerVastagosFk(idUsu: Int){
        viewModelScope.launch {
            state = state.copy(
                nombresVas = vasRepository.getVastagosPorFk(idUsu)
            )
        }
    }

    fun saveVastago(){
        val vastago = Vastago(
            id = state.id,
            nombreVas = state.nombreVas ?: "Cain",
            clan = state.clanVas ?: "Nosferatu",
            experiencia = state.experiencia ?: 0,
            generacion = state.generacion ?: 0,
            //Atributos
            fuerza = state.fuerza ?: 0,
            destreza = state.destreza ?: 0,
            resistencia = state.resistencia ?: 0,
            carisma = state.carisma ?: 0,
            manipulacion = state.manipulacion ?: 0,
            compostura = state.compostura ?: 0,
            inteligencia = state.inteligencia ?: 0,
            astucia = state.astucia ?: 0,
            resolucion = state.resolucion ?: 0,
            salud = state.salud ?: 0,
            fuerza_voluntad = state.fuerza_voluntad ?: 0,
            //FKs
            fkvas_usu = state.fkvas_usu ?: 1,
            fkvas_clan = state.fkvas_clan ?: 1,
            //Habilidades
            armas_de_fuego = state.armas_de_fuego?:0,
            artesania = state.artesania?:0,
            atletismo = state.atletismo?:0,
            conducir = state.conducir?:0,
            pelea = state.pelea?:0,
            pelea_con_armas = state.pelea_con_armas?:0,
            superviviencia = state.superviviencia?:0,
            callejeo = state.callejeo?:0,
            etiqueta = state.etiqueta?:0,
            interpretacion = state.interpretacion?:0,
            liderazgo = state.liderazgo?:0,
            perspicacia = state.perspicacia?:0,
            persuasion = state.persuasion?:0,
            subterfugio = state.subterfugio?:0,
            trato_con_animales = state.trato_con_animales?:0,
            academicismo = state.academicismo?:0,
            ciencias = state.ciencias?:0,
            consciencia = state.consciencia?:0,
            finanzas = state.finanzas?:0,
            investigacion = state.investigacion?:0,
            medicina = state.medicina?:0,
            ocultismo = state.ocultismo?:0,
            politica = state.politica?:0,
            tecnologia = state.tecnologia?:0
        )
        viewModelScope.launch {
            vasRepository.insertVastago(vastago)
        }
    }
    fun UpdateVastago(idAct: Int){
        val vastago = Vastago(
            id = state.id,
            nombreVas = state.nombreVas ?: "Cain",
            clan = state.clanVas ?: "Nosferatu",
            experiencia = state.experiencia ?: 0,
            generacion = state.generacion ?: 0,
            //Atributos
            fuerza = state.fuerza ?: 0,
            destreza = state.destreza ?: 0,
            resistencia = state.resistencia ?: 0,
            carisma = state.carisma ?: 0,
            manipulacion = state.manipulacion ?: 0,
            compostura = state.compostura ?: 0,
            inteligencia = state.inteligencia ?: 0,
            astucia = state.astucia ?: 0,
            resolucion = state.resolucion ?: 0,
            salud = state.salud ?: 0,
            fuerza_voluntad = state.fuerza_voluntad ?: 0,
            //FKs
            fkvas_usu = state.fkvas_usu ?: 1,
            fkvas_clan = state.fkvas_clan ?: 1,
            //Habilidades
            armas_de_fuego = state.armas_de_fuego?:0,
            artesania = state.artesania?:0,
            atletismo = state.atletismo?:0,
            conducir = state.conducir?:0,
            pelea = state.pelea?:0,
            pelea_con_armas = state.pelea_con_armas?:0,
            superviviencia = state.superviviencia?:0,
            callejeo = state.callejeo?:0,
            etiqueta = state.etiqueta?:0,
            interpretacion = state.interpretacion?:0,
            liderazgo = state.liderazgo?:0,
            perspicacia = state.perspicacia?:0,
            persuasion = state.persuasion?:0,
            subterfugio = state.subterfugio?:0,
            trato_con_animales = state.trato_con_animales?:0,
            academicismo = state.academicismo?:0,
            ciencias = state.ciencias?:0,
            consciencia = state.consciencia?:0,
            finanzas = state.finanzas?:0,
            investigacion = state.investigacion?:0,
            medicina = state.medicina?:0,
            ocultismo = state.ocultismo?:0,
            politica = state.politica?:0,
            tecnologia = state.tecnologia?:0
        )
        viewModelScope.launch {
            vasRepository.insertVastago(vastago)
        }
    }

    fun setNombreVas(nombre: String) {
        state = state.copy(nombreVas = nombre)
    }

    fun setClan(clan: String) {
        state = state.copy(clanVas = clan)
    }

    fun setGeneracion(generacion: Int) {
        state = state.copy(generacion = generacion)
    }

    //Obtener disciplinas por vastago
    fun getDisciplinasClanDeVas(vastagoId: Int){
        viewModelScope.launch {
            state = state.copy(
                listaDisciplinasPorVas =  vasRepository.getDisciplinasClanDeVas(vastagoId)
            )
        }
    }

    //Obtener disciplinas por Clan
    fun getDisciplinasPorClan(vastagoClan: Int) {
        viewModelScope.launch {
            state = state.copy(
                listaDisciplinasPorClan = vasRepository.getDisciplinasPorClan(vastagoClan)
            )
        }
    }

     fun ObtenerPoderes(vastagoId: Int){
        viewModelScope.launch {
            state = state.copy(
                listaPoderes =  vasRepository.getPoderes(vastagoId)
            )

        }
    }








    //Clanes
    // Obtener la lista de clanes desde la base de datos
    fun obtenerClanes() {
        viewModelScope.launch {
            state = state.copy(
                nombreClanes = clanRepository.getClanes()
            )
        }
    }

    suspend fun obtenerIdPorNombreClan(clanVas: String): Int {
        var idClan = -1 // Valor por defecto si no se encuentra ningún ID válido
        idClan = clanRepository.obtenerIdPorNombreClan(clanVas)
        return idClan
    }

    fun setFkvas_clan(fk: Int){
        state = state.copy(fkvas_clan = fk)
    }


}

