package com.proyecto

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.bbdd.entity.DisciplinasVas
import com.proyecto.bbdd.entity.PoderesVas
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.entity.Vastago
import com.proyecto.bbdd.repository.ClanRepository
import com.proyecto.bbdd.repository.DisciplinasVasRepository
import com.proyecto.bbdd.repository.PoderesVasRepository
import com.proyecto.bbdd.repository.UsuarioRepository
import com.proyecto.bbdd.repository.VastagoRepository
import kotlinx.coroutines.launch


class MPViewModel(
    private  val usuRepository: UsuarioRepository,
    private  val vasRepository: VastagoRepository,
    private  val clanRepository: ClanRepository,
    private  val disciplinasVasRepository : DisciplinasVasRepository,
    private  val PoderesVasRepository : PoderesVasRepository,


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

    fun cambiarND(dados: MutableState<Int>){
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
            // columna 1
            armas_de_fuego = state.armas_de_fuego?:0,
            artesania = state.artesania?:0,
            atletismo = state.atletismo?:0,
            conducir = state.conducir?:0,
            latrocinio = state.latrocinio ?: 0,
            pelea = state.pelea?:0,
            pelea_con_armas = state.pelea_con_armas?:0,
            sigilo = state.sigilo ?: 0,
            superviviencia = state.superviviencia?:0,
            // columna 2
            callejeo = state.callejeo?:0,
            etiqueta = state.etiqueta?:0,
            interpretacion = state.interpretacion?:0,
            intimidacion = state.intimidacion ?: 0,
            liderazgo = state.liderazgo?:0,
            perspicacia = state.perspicacia?:0,
            persuasion = state.persuasion?:0,
            subterfugio = state.subterfugio?:0,
            trato_con_animales = state.trato_con_animales?:0,
            // columna 3
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
            vasRepository.actualizarVastago(vastago)
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
    //obtener la id de la disciplinas del clan
    fun getIdDisciplinasPorClan(vastagoClan: Int) {
        viewModelScope.launch {
            state = state.copy(
                listaIdDisciplinas = vasRepository.getIdDisciplinasPorClan(vastagoClan)
            )
        }
    }

    suspend fun ObtenerPoderes(vastagoId: Int, idDisciplina: Int): List<String> {
        val poderes = vasRepository.getPoderes(vastagoId, idDisciplina)
        state = state.copy(listaPoderes = poderes)
        return poderes
    }

     //sacar al ultimo vastago creado
     fun UltimoVas(){
        viewModelScope.launch {
            state = state.copy(
                fk_vas = vasRepository.getUltIdVas()
            )
        }
    }


    //disciplinas
    fun saveDisciplinaVas(idDicplinas: Int, nivel: Int){
        val disciplinasVas =  DisciplinasVas(
            fkDisciplinasVas = idDicplinas,
            nivel = nivel,
            fk_vas = state.fk_vas,
        )
        viewModelScope.launch {
            disciplinasVasRepository.saveDisciplinasVas(disciplinasVas)
        }
    }

    //obtener disciplinas
    suspend fun obteneridDisciplina(idDisciplina: Int, fkVas: Int): Int{
        return disciplinasVasRepository.obtenerIdDisciplina(idDisciplina, fkVas)
    }

    //obtener niveles de disciplinas
    suspend fun obtenernivelesDisciplinas(fkVas: Int): List<Int> {
        return disciplinasVasRepository.obtenernivelesDisciplinas(fkVas)
    }

    //guardar vastago y sus disciplinas
    fun saveVastagoYDisciplinas(puntos: List<Int>, listaIdDisciplinas: List<Int>, onSuccess: () -> Unit) {
        viewModelScope.launch {
            // Guarda el vástago y espera a que se complete
            saveVastago()

            // Obtén el último vástago
            val ultimoVastago = UltimoVas()

            // Guarda las disciplinas asociadas al último vástago
            if (ultimoVastago != null) {
                listaIdDisciplinas.forEachIndexed { index, disciplinaId ->
                    saveDisciplinaVas(disciplinaId, puntos[index])
                }
            }

            // Navega después de completar todo
            onSuccess()
        }
    }
    fun guardarVastagoConDisciplinas(puntos: List<Int>, listaIdDisciplinas: List<Int>, onComplete: () -> Unit) {
        viewModelScope.launch {
            // 1. Guardar el vástago y obtener su ID
            val vastagoId: Long = vasRepository.insertVastago(
                Vastago(
                    nombreVas = state.nombreVas ?: "Cain",
                    clan = state.clanVas ?: "Nosferatu",
                    experiencia = state.experiencia ?: 0,
                    generacion = state.generacion ?: 0,
                    // Atributos
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
                    fkvas_usu = state.fkvas_usu ?: 1,
                    fkvas_clan = state.fkvas_clan ?: 1,
                    // Habilidades
                    armas_de_fuego = state.armas_de_fuego ?: 0,
                    artesania = state.artesania ?: 0,
                    atletismo = state.atletismo ?: 0,
                    conducir = state.conducir ?: 0,
                    pelea = state.pelea ?: 0,
                    pelea_con_armas = state.pelea_con_armas ?: 0,
                    superviviencia = state.superviviencia ?: 0,
                    callejeo = state.callejeo ?: 0,
                    etiqueta = state.etiqueta ?: 0,
                    interpretacion = state.interpretacion ?: 0,
                    liderazgo = state.liderazgo ?: 0,
                    perspicacia = state.perspicacia ?: 0,
                    persuasion = state.persuasion ?: 0,
                    subterfugio = state.subterfugio ?: 0,
                    trato_con_animales = state.trato_con_animales ?: 0,
                    academicismo = state.academicismo ?: 0,
                    ciencias = state.ciencias ?: 0,
                    consciencia = state.consciencia ?: 0,
                    finanzas = state.finanzas ?: 0,
                    investigacion = state.investigacion ?: 0,
                    medicina = state.medicina ?: 0,
                    ocultismo = state.ocultismo ?: 0,
                    politica = state.politica ?: 0,
                    tecnologia = state.tecnologia ?: 0
                )
            ) // El valor 'vastagoId' es de tipo Long

            // 2. Asegúrate de convertir el 'vastagoId' de Long a Int de manera segura
            val vastagoIdInt = vastagoId.toInt() // Convierte el ID de Long a Int
            state.id = vastagoIdInt
            // 3. Guardar las disciplinas asociadas al vástago recién insertado
            listaIdDisciplinas.forEachIndexed { index, disciplinaId ->
                disciplinasVasRepository.saveDisciplinasVas(
                    DisciplinasVas(
                        fkDisciplinasVas = disciplinaId,
                        nivel = puntos[index],
                        fk_vas = vastagoIdInt // Usamos el ID convertido a Int
                    )
                )
            }

            // 4. Llamar a la función final cuando _todo esté completo
            onComplete()
        }
    }

    fun ActualizarVastagoConDisciplinas(puntos: List<Int>, listaIdDisciplinas: List<Int>, onComplete: () -> Unit) {
        viewModelScope.launch {
            // 1. Guardar el vástago y obtener su ID
            vasRepository.actualizarVastago(
                Vastago(
                    id = state.id?: 1,
                    nombreVas = state.nombreVas ?: "Cain",
                    clan = state.clanVas ?: "Nosferatu",
                    experiencia = state.experiencia ?: 0,
                    generacion = state.generacion ?: 0,
                    // Atributos
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
                    fkvas_usu = state.fkvas_usu ?: 1,
                    fkvas_clan = state.fkvas_clan ?: 1,
                    // Habilidades
                    armas_de_fuego = state.armas_de_fuego ?: 0,
                    artesania = state.artesania ?: 0,
                    atletismo = state.atletismo ?: 0,
                    conducir = state.conducir ?: 0,
                    pelea = state.pelea ?: 0,
                    pelea_con_armas = state.pelea_con_armas ?: 0,
                    superviviencia = state.superviviencia ?: 0,
                    callejeo = state.callejeo ?: 0,
                    etiqueta = state.etiqueta ?: 0,
                    interpretacion = state.interpretacion ?: 0,
                    liderazgo = state.liderazgo ?: 0,
                    perspicacia = state.perspicacia ?: 0,
                    persuasion = state.persuasion ?: 0,
                    subterfugio = state.subterfugio ?: 0,
                    trato_con_animales = state.trato_con_animales ?: 0,
                    academicismo = state.academicismo ?: 0,
                    ciencias = state.ciencias ?: 0,
                    consciencia = state.consciencia ?: 0,
                    finanzas = state.finanzas ?: 0,
                    investigacion = state.investigacion ?: 0,
                    medicina = state.medicina ?: 0,
                    ocultismo = state.ocultismo ?: 0,
                    politica = state.politica ?: 0,
                    tecnologia = state.tecnologia ?: 0
                )
            )

            // 3. Guardar las disciplinas asociadas al vástago recién insertado
            listaIdDisciplinas.forEachIndexed { index, disciplinaId ->
                disciplinasVasRepository.actualizarDisciplinasVas(
                    DisciplinasVas(
                        fkDisciplinasVas = disciplinaId,
                        nivel = puntos[index],
                        fk_vas = state.id?: 1
                    )
                )
            }

            // 4. Llamar a la función final cuando _todo esté completo
            onComplete()
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

    //poderes
    fun guardarPoderes(nombre: String, fkDisciplinas: Int) {
        viewModelScope.launch {
            val poderExistente = PoderesVasRepository.obtenerPoder(fkDisciplinas, nombre)

            val poder = PoderesVas(
                nombre = nombre,
                fk_disciplinas = fkDisciplinas
            )
            if (poderExistente == null) {
                PoderesVasRepository.insertPoderesVas(poder) // Asume que tienes un repositorio configurado
            }else {
                // Actualizar si ya existe (opcional)
                PoderesVasRepository.actualizarPoder(poderExistente.copy(nombre = nombre))
            }
        }
    }


}

