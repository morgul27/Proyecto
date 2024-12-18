package com.proyecto.screens.mod_historia.nosferatu

import com.proyecto.screens.mod_historia.MenuTirardas
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.MPViewModel
import com.proyecto.R
import com.proyecto.SharedViewModel
import com.proyecto.navigation.Screens
import com.proyecto.ui.theme.Blanco
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.DefaultButton
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import com.proyecto.ui.theme.VerdeMarmol
import com.proyecto.ui.theme.ghoticFamily

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun P51Nosf(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    val image = painterResource(R.drawable.marmol)
    ProyectoTheme {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme,
            typography = Typography,
            content = {

                Scaffold (
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent,
                                titleContentColor = Borgoña
                            ),
                            title = { Text(
                                text = "Modo Historia",
                                fontFamily = ghoticFamily,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = Borgoña,
                                fontSize = 35.sp
                            )
                            }
                        )
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Image(
                            painter = image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.alpha(0.6F)
                        )
                    }

                    val state = viewModel.state
                    Log.d("MIRA AQUI P2NOSF", "El booleano: ${viewModel.showSecondMenuHist.value}")
                    if (viewModel.showSecondMenuHist.value == true) {
                        //Menu de dados
                        MenuVerTirada2P51Nosf(navController, viewModel, viewModel.nDados, viewModel.dificult[viewModel.movDif])
                    } else {
                        //Menu con texto
                        BodyP51Nosf(navController, viewModel, sharedViewModel)
                    }
                }
            }
        )
    }
}

@Composable
fun BodyP51Nosf(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    val state = viewModel.state

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 55.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = stringResource(R.string.p51TextN),
                color = Blanco
            )

            Spacer(modifier = Modifier.height(25.dp))

            DefaultButton(onClick = {
                viewModel.cambiarND(sharedViewModel.vasMan)
                viewModel.movDif = 1
                viewModel.cambioMenuHist()
            },
                text = ("Tirada de Manipulacion")
            )

            Spacer(modifier = Modifier.height(30.dp))
            DefaultButton(onClick = { navController.navigate(route = Screens.MenuPrincipal.route) },
                "Volver al menu principal")
            Spacer(modifier = Modifier.height(50.dp))

        }
    }
}


//Menu bueno
@Composable
fun MenuVerTirada2P51Nosf(navController: NavController, viewModel: MPViewModel, nUsuario : Int, nNecesario: Int) {
    val direccion = { navController.navigate(route = Screens.P6Nosf.route) } //rellenar esto 6
    val direccion2 = { navController.navigate(route = Screens.P7Nosf.route) } //rellenar esto 7
    var textoBueno = "Has ganado"
    var textoMalo = "Has fallado la tirada"


    MenuTirardas(viewModel, nUsuario, nNecesario, navigateTo = direccion, navigateTo2 = direccion2, textoBueno = textoBueno , textoMalo = textoMalo)

}
