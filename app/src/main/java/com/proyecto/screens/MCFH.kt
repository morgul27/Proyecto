package com.proyecto.screens

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.MPViewModel
import com.proyecto.R
import com.proyecto.SharedViewModel
import com.proyecto.navigation.Screens
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.DefaultButton
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import com.proyecto.ui.theme.ghoticFamily

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MCFH(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    val image = painterResource(R.drawable.marmol)
    ProyectoTheme {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme,
            typography = Typography,
            content = {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent,
                                titleContentColor = Borgoña,
                                navigationIconContentColor = Borgoña
                            ),
                            title = {
                                Text(
                                    text = "Creación",
                                    fontFamily = ghoticFamily,
                                    maxLines = 1,
                                    color = Borgoña,
                                    fontSize = 35.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentSize(Alignment.Center)
                                        .padding(end = 40.dp)
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Arrow back"
                                    )
                                }
                            }
                        )
                    }) {
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
                    if (viewModel.showSecondMenu.value) {
                        MDFSecondBodyContent(navController, viewModel, sharedViewModel)

                    } else {
                        // Mostrar el menú principal
                        MDFHBody(navController, viewModel, sharedViewModel)

                    }
                }
            }
        )
    }
}

@Composable
fun MDFHBody(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel) {
    val state = viewModel.state

    Log.d("MIRA AQUI", "Fuerzaaaaaaaaaaaaaaa: ${state.fuerza}")
    Log.d("MIRA AQUI", "Resolucion: ${state.resolucion}")

    var puntosT = remember { mutableStateOf(29) } //polifacético
    //equilibrado 26
    //especialista 22
    val atributos = remember {
        mutableStateListOf(
            "Fuerza",
            "Destreza",
            "Resistencia",
            "Carisma",
            "Manipulación",
            "Compostura",
            "Inteligencia",
            "Astucia",
            "Resolución"
        )
    }

    val textoExplicacion = remember {
        mutableStateListOf(
            "Fuerza",
            "Destreza",
            "Resistencia",
            "Carisma",
            "Manipulación",
            "Compostura",
            "Inteligencia",
            "Astucia",
            "Resolución"
        )
    }

    var puntos = remember { mutableStateListOf(0, 0, 0, 0, 0, 0, 0, 0, 0) }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Espacio adicional
        item {
            Spacer(modifier = Modifier.height(25.dp))

            //botones
            DefaultButton(
                onClick = {

                    atributos.forEachIndexed { index, atributo ->
                        // Llama a la función para actualizar el estado
                        //updateState(atributo, puntos[index], viewModel)
                    }

                    navController.navigate(route = Screens.MCFD.route)
                },
                text = "Guardar"
            )

            DefaultButton(
                onClick = { navController.popBackStack() },
                text = "Volver"
            )
        }
        }
}