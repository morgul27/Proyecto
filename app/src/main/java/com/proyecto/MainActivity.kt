package com.proyecto

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.proyecto.bbdd.UsuarioDatabase
import com.proyecto.bbdd.entity.Amalgama
import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.entity.DisciplinasClan
import com.proyecto.bbdd.entity.NNClanDisciplinas
import com.proyecto.bbdd.entity.Poderes
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.interfaces.AmalgamaDao
import com.proyecto.bbdd.interfaces.ClanDao
import com.proyecto.bbdd.interfaces.DisciplinasClanDao
import com.proyecto.bbdd.interfaces.NNClanDisciplinasDao
import com.proyecto.bbdd.interfaces.PoderesDao
import com.proyecto.bbdd.repository.AmalgamaRepository
import com.proyecto.bbdd.repository.ClanRepository
import com.proyecto.bbdd.repository.DisciplinasClanRepository
import com.proyecto.bbdd.repository.NNClanDisciplinasRepository
import com.proyecto.bbdd.repository.PoderesRepository
import com.proyecto.bbdd.repository.UsuarioRepository
import com.proyecto.bbdd.repository.VastagoRepository
import com.proyecto.navigation.Navegacion
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var creacionDatos: CreacionDatos


    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoTheme {
                MaterialTheme(
                    colorScheme = colorScheme,
                    typography = Typography,
                    content = {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {

                            //Creo la base de datos
                            val db = Room.databaseBuilder(
                                this,
                                UsuarioDatabase::class.java,
                                "base_de_datos"
                            ).build()

                            //llamo a los dao
                            val dao = db.dao
                            val vasDao = db.vasDao
                            val clanDao = db.clanDao
                            val disciplinasClanDao = db.disciplinasClanDao
                            val poderesDao = db.poderesDao
                            val NNClanDisciplinasDao = db.nnClanDisciplinasDao
                            val amalgamaDao = db.amalgamaDao

                            //llamo a los repositorios
                            val usuRepository = UsuarioRepository(dao)
                            val vasRepository = VastagoRepository(vasDao)
                            val clanRepository = ClanRepository(clanDao)
                            val disciplinasClanRepository = DisciplinasClanRepository(disciplinasClanDao)
                            val poderesRepository = PoderesRepository(poderesDao)
                            val NNClanDisciplinasRepository = NNClanDisciplinasRepository(NNClanDisciplinasDao)
                            val AmalgamaRepository = AmalgamaRepository(amalgamaDao)

                            //creo la variable del ViewModel
                            val viewModel =
                                MPViewModel(usuRepository, vasRepository, clanRepository)

                            // Inicializo la clase CreacionDatos
                            creacionDatos = CreacionDatos()

                            // Verificar si la tabla está vacía
                            lifecycleScope.launch {
                                val isEmpty = clanDao.getClan().isEmpty()
                                if (isEmpty) {
                                    // Insertar los datos iniciales si la tabla está vacía
                                    creacionDatos.insertInitialDataClan(clanDao)
                                    creacionDatos.insertInitialDataDisciplinas(disciplinasClanDao)
                                    creacionDatos.insertNNClanDisciplinas(NNClanDisciplinasDao)
                                    creacionDatos.insertPoderes(poderesDao)
                                    creacionDatos.insertInitialDataAmalgama(amalgamaDao)
                                }
                            }


                            Navegacion(viewModel)

                        }
                    }
                )
            }
        }
    }


    // Metodo para manejar los cambios de configuración manualmente
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}



private suspend fun insertInitialDataAmalgama(dao: AmalgamaDao) {
    //Animalismo
    // sentir a la bestia
    dao.insertAmalgama(Amalgama(
        1,
        fkvas_poder = 1,
        fkvas_disciplina_principal = 1,
        nivel_disciplina_principal = 1
        ))

    //vínculo con famulus
    dao.insertAmalgama(Amalgama(
        2,
        fkvas_poder = 2,
        fkvas_disciplina_principal = 1,
        nivel_disciplina_principal = 1
    ))

    //susurros salvajes
    dao.insertAmalgama(Amalgama(
        3,
        fkvas_poder = 3,
        fkvas_disciplina_principal = 1,
        nivel_disciplina_principal = 2
    ))

    //colmena no-muerta
    dao.insertAmalgama(Amalgama(
        4,
        fkvas_poder = 4,
        fkvas_disciplina_principal = 1,
        fkvas_disciplina_secundaria = 2, //Ofuscacion
        nivel_disciplina_principal = 3,
        nivel_disciplina_secundaria = 2
    ))

    //reprimir a la bestia
    dao.insertAmalgama(Amalgama(
        5,
        fkvas_poder = 5,
        fkvas_disciplina_principal = 1,
        nivel_disciplina_principal = 3
    ))

    //suculencia animal
    dao.insertAmalgama(Amalgama(
        6,
        fkvas_poder = 6,
        fkvas_disciplina_principal = 1,
        nivel_disciplina_principal = 3
    ))

    //comunión de espíritus
    dao.insertAmalgama(Amalgama(
        7,
        fkvas_poder = 7,
        fkvas_disciplina_principal = 1,
        nivel_disciplina_principal = 4
    ))

    //control animal
    dao.insertAmalgama(Amalgama(
        8,
        fkvas_poder = 8,
        fkvas_disciplina_principal = 1,
        nivel_disciplina_principal = 5
    ))

    //expulsar a la bestia
    dao.insertAmalgama(Amalgama(
        9,
        fkvas_poder = 9,
        fkvas_disciplina_principal = 1,
        nivel_disciplina_principal = 5,
    ))

    //Ofuscación
    //capa de sombras




   // sin hacer
    dao.insertAmalgama(Amalgama(
        1,
        fkvas_poder = 1,
        fkvas_disciplina_principal = 1,
        fkvas_disciplina_secundaria = 1,
        nivel_disciplina_principal = 1,
        nivel_disciplina_secundaria = 1,
        requisito_poder = 1
    ))
    dao.insertAmalgama(Amalgama(
        1,
        fkvas_poder = 1,
        fkvas_disciplina_principal = 1,
        fkvas_disciplina_secundaria = 1,
        nivel_disciplina_principal = 1,
        nivel_disciplina_secundaria = 1,
        requisito_poder = 1
    ))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoTheme {
        //Navegacion()
    }
}