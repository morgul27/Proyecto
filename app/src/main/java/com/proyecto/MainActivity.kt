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
import com.proyecto.bbdd.entity.DisciplinasVas
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

                            val amalgamaDao = db.amalgamaDao
                            val clanDao = db.clanDao
                            val disciplinasClanDao = db.disciplinasClanDao
                            val disciplinasVasDao = db.disciplinasVasDao
                            val NNClanDisciplinasDao = db.nnClanDisciplinasDao
                            val poderesDao = db.poderesDao
                            val poderesVas = db.poderesVasDao
                            val vasDao = db.vasDao

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

//                            lifecycleScope.launch {
//                                disciplinasVasDao.insertDisciplinasVas(
//                                    DisciplinasVas(
//                                        idDisciplinasVas = 1,
//                                        nivel = 5,
//                                        fk_vas = 1
//                                    )
//                                )
//                            }

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




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoTheme {
        //Navegacion()
    }
}