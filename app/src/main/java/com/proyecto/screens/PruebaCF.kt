package com.proyecto.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
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
fun PruebaCF(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
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
                    Log.e("exp", "${sharedViewModel.vasExp.value}")
                    var exp = sharedViewModel.vasExp.value ?: 1111
                    // Mostrar el menú principal
                    CFBody(navController, viewModel, sharedViewModel, exp)
                }
            }
        )
    }
}


@Composable
fun CFBody(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel, exp: Int) {
    val state = viewModel.state
    var exp2 by remember { mutableStateOf(exp) }

    //pruebas
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


    Log.e("lista2", "discipli: ${state.listaDisciplinasPorClan}")
    // Pantalla visualmente
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
            Text("Experiencia total: ${exp2}")
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
                    Box (Modifier.size(180.dp, 45.dp)) {
                        Button(
                            onClick = {
                                val expCalculada = exp2 + calculo(puntos[index])
                                if (puntos[index] > 0) {
                                    puntos[index] -= 1
                                    exp2 = expCalculada
                                    updateA(atributos[index], puntos[index], exp2, viewModel)
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
                                viewModel.state.experiencia = exp2
                                val resto = calculo(puntos[index] + 1)
                                val expCalculada = exp2 - calculo(puntos[index] + 1)
                                if (puntos[index] < 6 && resto <= exp2) {
                                    puntos[index] += 1
                                    exp2 = expCalculada
                                    updateA(atributos[index], puntos[index], exp2, viewModel)
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

        // Botón de guardar
        item {
            Spacer(modifier = Modifier.height(25.dp))

            DefaultButton(onClick = {
                state.fuerza_voluntad = state.resolucion?.let { state.compostura?.plus(it) }!!
                state.salud = state.resistencia?.plus(3)!!
                viewModel.saveVastago()
                navController.navigate(route = Screens.MenuPrincipal.route)},
                text = "Guardar"
            )

            DefaultButton(onClick = { navController.popBackStack() },
                text = "Volver"
            )
        }

        // Espacio adicional debajo de los elementos
        item {
            // Mostrar el segundo menú
            DefaultButton(
                onClick = { viewModel.toggleSecondMenu() },
                modifier = Modifier.padding(16.dp),
                text = "Leyenda"
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


//Calcular la experiencia
private fun calculo(valorAnt: Int): Int {
    var cal: Int
    cal = valorAnt * 5
    return cal
}


// Función para actualizar los valores de los atributos en el estado
fun updateA(atributo: String, nuevoValor: Int, exp3: Int, viewModel: MPViewModel) {
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

    Log.d("MIRA AQUI", "expereciaaaaaaaaaaaa: ${viewModel.state.experiencia}")
    Log.d("MIRA AQUI", "exp3: ${exp3}")
}