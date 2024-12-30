package com.proyecto.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.proyecto.dialog.explicacion
import com.proyecto.navigation.Screens
import com.proyecto.ui.theme.Blanco
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.DefaultButton
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import com.proyecto.ui.theme.ghoticFamily

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MCFA(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
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
                        MDFABody(navController, viewModel, sharedViewModel)

                    }
                }
            }
        )
    }
}


@Composable
fun MDFABody(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel) {
    val state = viewModel.state
    var puntosT = remember { mutableStateOf(22) }
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
            Text("Puntos totales: ${puntosT.value}")
            Spacer(modifier = Modifier.height(25.dp))
        }

        //Botones
        puntos.forEachIndexed { index, _ ->
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(Modifier.size(200.dp, 45.dp)) {
                        explicacion(
                            texto = atributos[index],
                            textoT = atributos[index],
                            textoExpl = textoExplicacion[index],
                            Modifier.align(Alignment.CenterStart),
                            fontSize = 20.sp,
                        )
                    }
                    //Modifier
                    Box(Modifier.size(180.dp, 45.dp)) {
                        Button(
                            onClick = {
                                if (puntos[index] > 0) {
                                    puntos[index] -= 1
                                    puntosT.value += 1
                                }
                            },
                            Modifier.align(Alignment.CenterStart),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Borgoña,
                                contentColor = Blanco
                            ),
                            shape = RoundedCornerShape(50, 6, 6, 50)
                        ) {
                            Text("-")
                        }

                        Text(
                            text = "${puntos[index]}",
                            Modifier.align(Alignment.Center)
                        )

                        Button(
                            onClick = {
                                if (puntos[index] < 6 && puntosT.value > 0) {
                                    puntos[index] += 1
                                    puntosT.value -= 1
                                }
                            },
                            Modifier.align(Alignment.CenterEnd),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Borgoña,
                                contentColor = Blanco
                            ),
                            shape = RoundedCornerShape(6, 50, 50, 6)
                        ) {
                            Text("+")
                        }
                    }

                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(25.dp))


            //botones
            DefaultButton(onClick = {

                atributos.forEachIndexed { index, atributo ->
                    // Llama a la función para actualizar el estado
                    updateState(atributo, puntos[index], viewModel)
                }
                state.fuerza_voluntad = state.resolucion.let { state.compostura.plus(it) }
                state.salud = state.resistencia.plus(3)
                navController.navigate(route = Screens.MCFH.route)},
                text = "Siguiente"
            )

            DefaultButton(onClick = { navController.popBackStack() },
                text = "Volver"
            )
        }
    }
}

// Función para actualizar los valores de los atributos en el estado
fun updateState(atributo: String, nuevoValor: Int, viewModel: MPViewModel,) {
    Log.d("MIRA AQUI", "atributo: ${atributo}")
    when (atributo) {
        "Fuerza" -> viewModel.state.fuerza = nuevoValor
        "Destreza" -> viewModel.state.destreza = nuevoValor
        "Resistencia" -> viewModel.state.resistencia = nuevoValor
        "Carisma" -> viewModel.state.carisma = nuevoValor
        "Manipulación" -> viewModel.state.manipulacion = nuevoValor
        "Compostura" -> viewModel.state.compostura = nuevoValor
        "Inteligencia" -> viewModel.state.inteligencia = nuevoValor
        "Astucia" -> viewModel.state.astucia = nuevoValor
        "Resolución" -> viewModel.state.resolucion = nuevoValor
        else -> Log.e("MDF2BodyContent", "Atributo desconocido: $atributo")
    }
}