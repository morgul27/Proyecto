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
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.interfaces.ClanDao
import com.proyecto.bbdd.repository.ClanRepository
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

                            //llamo a los repositorios
                            val usuRepository = UsuarioRepository(dao)
                            val vasRepository = VastagoRepository(vasDao)
                            val clanRepository = ClanRepository(clanDao)

                            //creo la variable del ViewModel
                            val viewModel =
                                MPViewModel(usuRepository, vasRepository, clanRepository)


                            // Verificar si la tabla está vacía
                            GlobalScope.launch {
                                val isEmpty = clanDao.getClan().isEmpty()
                                if (isEmpty) {
                                    // Insertar los datos iniciales si la tabla está vacía
                                    insertInitialData(clanDao)
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
private suspend fun insertInitialData(dao: ClanDao) {
    // Insertar los datos de ejemplo
    dao.insertClan(Clan(1, nombreClan = "Nosferatu"))
    dao.insertClan(Clan(2, nombreClan = "Gangrel"))
    dao.insertClan(Clan(3, nombreClan = "Brujah"))

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoTheme {
        //Navegacion()
    }
}