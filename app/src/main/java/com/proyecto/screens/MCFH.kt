package com.proyecto.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
    val habilidades = remember {
        mutableStateListOf(
            //columna 1
            "Armas de Fuego",
            "Artesanía",
            "Atletismo",
            "Conducir",
            "Latrocinio",
            "Pelea",
            "Pelea con Armas",
            "Sigilo",
            "Superviviencia",
            //columna 2
            "Callejeo",
            "Etiqueta",
            "Interpretación",
            "Intimidación",
            "Liderazgo",
            "Perspicacia",
            "Persuasion",
            "Subterfugio",
            "Trato con animales",
            //columna 3
            "Academicismo",
            "Ciencias",
            "Consciencia",
            "Finanzas",
            "Investigacion",
            "Medicina",
            "Ocultismo",
            "Politica",
            "Tecnología",
        )
    }

    val textoExplicacion = remember {
        mutableStateListOf(
            //columna 1
            "Armas de Fuego",
            "Artesanía",
            "Atletismo",
            "Conducir",
            "Latrocinio",
            "Pelea",
            "Pelea con Armas",
            "Sigilo",
            "Superviviencia",
            //columna 2
            "Callejeo",
            "Etiqueta",
            "Interpretación",
            "Intimidación",
            "Liderazgo",
            "Perspicacia",
            "Persuasion",
            "Subterfugio",
            "Trato con animales",
            //columna 3
            "Academicismo",
            "Ciencias",
            "Consciencia",
            "Finanzas",
            "Investigacion",
            "Medicina",
            "Ocultismo",
            "Politica",
            "Tecnología",
        )
    }
    //27 habilidades
    var puntos = remember {
        mutableStateListOf(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 25.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(25.dp))
        Text("puntos totales: ${puntosT.value}")
        Spacer(modifier = Modifier.height(25.dp))
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 75.dp),
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
                    Box(Modifier
                        .width(200.dp)
                        .heightIn(min = 45.dp, max = 80.dp)
                    ) {
                        explicacion(
                            texto = habilidades[index],
                            textoT = habilidades[index],
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
                    Spacer(modifier = Modifier.height(55.dp))
                }
            }
        }
        // Espacio adicional
        item {
            Spacer(modifier = Modifier.height(25.dp))

            //botones
            DefaultButton(
                onClick = {
                    habilidades.forEachIndexed { index, habilidad ->
                        // Llama a la función para actualizar el estado
                        updateHabilidades(habilidad, puntos[index], viewModel)
                    }
                    navController.navigate(route = Screens.MCFD.route)
                },
                text = "Siguiente"
            )

            DefaultButton(
                onClick = { navController.popBackStack() },
                text = "Volver"
            )
        }
    }
}


// Función para actualizar los valores de los atributos en el estado
fun updateHabilidades(habilidades: String, nuevoValor: Int, viewModel: MPViewModel,) {
    Log.d("habilidades", "habilidades: ${habilidades}, nivel: ${nuevoValor}")
    when (habilidades) {
        "Armas de Fuego" -> viewModel.state.armas_de_fuego = nuevoValor
        "Artesanía" -> viewModel.state.artesania = nuevoValor
        "Atletismo" -> viewModel.state.atletismo = nuevoValor
        "Conducir" -> viewModel.state.conducir = nuevoValor
        "Latrocinio" -> viewModel.state.latrocinio = nuevoValor
        "Pelea" -> viewModel.state.pelea = nuevoValor
        "Pelea con Armas" -> viewModel.state.pelea_con_armas = nuevoValor
        "Sigilo" -> viewModel.state.sigilo = nuevoValor
        "Superviviencia" -> viewModel.state.superviviencia = nuevoValor

        "Callejeo" -> viewModel.state.callejeo = nuevoValor
        "Etiqueta" -> viewModel.state.etiqueta = nuevoValor
        "Interpretación" -> viewModel.state.interpretacion = nuevoValor
        "Intimidación" -> viewModel.state.intimidacion = nuevoValor
        "Liderazgo" -> viewModel.state.liderazgo = nuevoValor
        "Perspicacia" -> viewModel.state.perspicacia = nuevoValor
        "Persuasion" -> viewModel.state.persuasion = nuevoValor
        "Subterfugio" -> viewModel.state.subterfugio = nuevoValor
        "Trato con animales" -> viewModel.state.trato_con_animales = nuevoValor

        "Academicismo" -> viewModel.state.academicismo = nuevoValor
        "Ciencias" -> viewModel.state.ciencias = nuevoValor
        "Consciencia" -> viewModel.state.consciencia = nuevoValor
        "Finanzas" -> viewModel.state.finanzas = nuevoValor
        "Investigacion" -> viewModel.state.investigacion = nuevoValor
        "Medicina" -> viewModel.state.medicina = nuevoValor
        "Ocultismo" -> viewModel.state.ocultismo = nuevoValor
        "Politica" -> viewModel.state.politica = nuevoValor
        "Tecnología" -> viewModel.state.tecnologia = nuevoValor
        else -> Log.e("MCFHBody", "Atributo desconocido: $habilidades")
    }
}