package com.proyecto.screens.mod_historia

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.MPViewModel
import com.proyecto.R
import com.proyecto.SharedViewModel
import com.proyecto.navigation.Screens
import com.proyecto.ui.theme.AzulPursiano
import com.proyecto.ui.theme.Blanco
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.DefaultButton
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import com.proyecto.ui.theme.ghoticFamily

private var finalMalo = false

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun InicioHist(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    val image = painterResource(R.drawable.marmol)
    //val verdeMarmolConOpacidad = VerdeMarmol.copy(alpha = 0.1f)
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


                    if (viewModel.showSecondMenuHist.value == true) {
                        //Menu de dados
                        MenuVerTirada2(navController, viewModel, viewModel.nDados, viewModel.dificult[viewModel.movDif])
                    } else {
                        //Menu con texto
                        BodyInicioHist(navController, viewModel, sharedViewModel)
                    }
                }
            }
        )
    }
}

@Composable
fun BodyInicioHist(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
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

            //Nosferatu
            if(sharedViewModel.fkClan.value == 1){
                Text(text = stringResource(R.string.iniNosf),
                    color = Blanco)
                Spacer(modifier = Modifier.height(50.dp))
                //boton 1
                DefaultButton(onClick = {
                    viewModel.cambiarND(sharedViewModel.vasIntel)
                    viewModel.movDif = 0
                    viewModel.cambioMenuHist()
                },
                    text = "Tirada de inteligencia"
                )

                //Gangrel
            }else if(sharedViewModel.fkClan.value == 2){
                Text(text = stringResource(R.string.iniGang) )
                Spacer(modifier = Modifier.height(50.dp))
                DefaultButton(onClick = {
                    navController.navigate(route = Screens.P2Gangr.route)
                },
                    text = "Continuar"
                )

                //Brujah
            }else{
                Text(text = stringResource(R.string.iniBr) )
                Spacer(modifier = Modifier.height(50.dp))
                DefaultButton(onClick = {
                    navController.navigate(route = Screens.P2Br.route)
                },
                    text = "Continuar"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            DefaultButton(onClick = { navController.navigate(route = Screens.MenuPrincipal.route) },
                "Volver al menu principal")
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}


//Menu bueno
@Composable
fun MenuVerTirada2(navController: NavController, viewModel: MPViewModel, nUsuario : Int, nNecesario: Int) {
    val direccion = { navController.navigate(route = Screens.P2Nosf.route) }  //camino 1
    val direccion2 = { navController.navigate(route = Screens.MenuPrincipal.route) } //camino2

    var textBueno = stringResource(R.string.p1GanasN)
    var textMalo = stringResource(R.string.p1PierdesN)

    MenuTirardas(viewModel, nUsuario, nNecesario, navigateTo = direccion, navigateTo2 = direccion2, textoBueno = textBueno , textoMalo = textMalo)

}



@Preview
@Composable
fun Preview(){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = AzulPursiano,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(0, 0, 0, 50)
        ) {
            Text(text = "boton 1")
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Borgoña,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50, 0, 0, 50)
        ) {
            Text(text = "boton 1")
        }

        TextButton(onClick = { /*TODO*/ },

            ) {
            Text(text = "Boton texto dsañreEÑ",
                color = Color.Red,
                fontFamily = ghoticFamily,
                fontSize = 37.sp,
                lineHeight = 100.sp,
            )
        }

    }
}