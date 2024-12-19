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
import androidx.room.Room
import com.proyecto.bbdd.UsuarioDatabase
import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.entity.DisciplinasClan
import com.proyecto.bbdd.entity.NNClanDisciplinas
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.interfaces.ClanDao
import com.proyecto.bbdd.interfaces.DisciplinasClanDao
import com.proyecto.bbdd.interfaces.NNClanDisciplinasDao
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
                            val NNClanDisciplinasDao = db.NNClanDisciplinasDao

                            //llamo a los repositorios
                            val usuRepository = UsuarioRepository(dao)
                            val vasRepository = VastagoRepository(vasDao)
                            val clanRepository = ClanRepository(clanDao)
                            val disciplinasClanRepository = DisciplinasClanRepository(disciplinasClanDao)
                            val poderesRepository = PoderesRepository(poderesDao)
                            val NNClanDisciplinasRepository = NNClanDisciplinasRepository(NNClanDisciplinasDao)

                            //creo la variable del ViewModel
                            val viewModel =
                                MPViewModel(usuRepository, vasRepository, clanRepository)


                            // Verificar si la tabla está vacía
                            GlobalScope.launch {
                                val isEmpty = clanDao.getClan().isEmpty()
                                if (isEmpty) {
                                    // Insertar los datos iniciales si la tabla está vacía
                                    insertInitialDataClan(clanDao)
                                    insertInitialDataDisciplinas(disciplinasClanDao)
                                    insertNNClanDisciplinas(NNClanDisciplinasDao)
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



// Función para insertar datos iniciales
private suspend fun insertInitialDataClan(dao: ClanDao) {
    // Insertar los datos
    dao.insertClan(Clan(1, nombreClan = "Nosferatu"))
    dao.insertClan(Clan(2, nombreClan = "Gangrel"))
    dao.insertClan(Clan(3, nombreClan = "Brujah"))

}
private suspend fun insertInitialDataDisciplinas(dao: DisciplinasClanDao) {
    // Nosferatu
    dao.insertDisciplinasClan(DisciplinasClan(1, nombre = "Animalismo"))
    dao.insertDisciplinasClan(DisciplinasClan(2, nombre = "Ofuscación"))
    dao.insertDisciplinasClan(DisciplinasClan(3, nombre = "Potencia"))
    //Gangrel
    dao.insertDisciplinasClan(DisciplinasClan(4, nombre = "Fortaleza"))
    dao.insertDisciplinasClan(DisciplinasClan(5, nombre = "Protean"))
    //Brujah
    dao.insertDisciplinasClan(DisciplinasClan(6, nombre = "Celeridad"))
    dao.insertDisciplinasClan(DisciplinasClan(7, nombre = "Presencia"))

}

private suspend fun insertNNClanDisciplinas(dao: NNClanDisciplinasDao) {
    // Nosferatu
    dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 1, fk_disc = 1)) //Animalismo
    dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 1, fk_disc = 2)) //Ofuscación
    dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 1, fk_disc = 3)) //Potencia
    //Gangrel
    dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 2, fk_disc = 1)) //Animalismo
    dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 2, fk_disc = 4)) //Fortaleza
    dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 2, fk_disc = 5)) //Protean
    //Brujah
    dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 3, fk_disc = 3)) //Potencia
    dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 3, fk_disc = 6)) //Celeridad
    dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 3, fk_disc = 7)) //Presencia
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoTheme {
        //Navegacion()
    }
}