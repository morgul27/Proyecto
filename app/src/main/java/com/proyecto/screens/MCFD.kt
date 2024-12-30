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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.runtime.LaunchedEffect
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
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import com.proyecto.ui.theme.ghoticFamily

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MCFD(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
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
                        MDFDBody(navController, viewModel, sharedViewModel)

                    }
                }
            }
        )
    }
}

@Composable
fun MDFDBody(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel) {
    val state = viewModel.state

    Log.d("Habilidad1", "armasfuego: ${state.armas_de_fuego}")
    Log.d("Habilidad2", "tecnologia: ${state.tecnologia}")

    val habilidades = remember {
        mutableStateListOf(
            "Fuerza", "Destreza", "Resistencia", "Carisma",
            "Manipulación", "Compostura", "Inteligencia",
            "Astucia", "Resolución", "Percepción",
            "Sigilo", "Supervivencia", "Tecnología",
            "Atletismo", "Empatía", "Ciencia", "Liderazgo", "Subterfugio", "Medicina"
        )
    }
    val niveles =
        remember { mutableStateListOf<Int>().apply { repeat(habilidades.size) { add(0) } } }

    // Estados para las reglas
    val nivel3Asignado = remember { mutableStateOf(false) }
    val nivel2Asignados = remember { mutableStateOf(0) }
    val maxNivel2 = 8

    // UI
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Distribución Polifacético")
            Spacer(modifier = Modifier.height(8.dp))

            // Lista de habilidades con interacción
            habilidades.forEachIndexed { index, habilidad ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(habilidad, modifier = Modifier.weight(1f))
                    Text("Nivel: ${niveles[index]}", modifier = Modifier.padding(horizontal = 8.dp))

                    // Botón para subir nivel, respetando las reglas
                    Button(
                        onClick = {
                            when (niveles[index]) {
                                0 -> {
                                    if (!nivel3Asignado.value) {
                                        niveles[index] = 3
                                        nivel3Asignado.value = true
                                    } else if (nivel2Asignados.value < maxNivel2) {
                                        niveles[index] = 2
                                        nivel2Asignados.value++
                                    }
                                }

                                2 -> {
                                    niveles[index] = 3
                                    nivel2Asignados.value--
                                    nivel3Asignado.value = true
                                }

                                3 -> {
                                    niveles[index] = 0
                                    nivel3Asignado.value = false
                                }
                            }
                        },
                        enabled = niveles[index] < 3
                    ) {
                        Text("+")
                    }

                    // Botón para bajar nivel
                    Button(
                        onClick = {
                            when (niveles[index]) {
                                3 -> {
                                    niveles[index] = 0
                                    nivel3Asignado.value = false
                                }

                                2 -> {
                                    niveles[index] = 0
                                    nivel2Asignados.value--
                                }
                            }
                        },
                        enabled = niveles[index] > 0
                    ) {
                        Text("-")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje de ayuda
            Text("Habilidad a nivel 3: ${if (nivel3Asignado.value) "Asignada" else "Pendiente"}")
            Text("Habilidades a nivel 2: ${nivel2Asignados.value}/$maxNivel2")

            // Botón para confirmar
            Button(
                onClick = { confirmarDistribucion(niveles, habilidades) },
                enabled = nivel3Asignado.value && nivel2Asignados.value == maxNivel2
            ) {
                Text("Confirmar")
            }
        }
    }
}

// Verifica si se puede subir el nivel según las reglas
fun puedeSubirNivel(nivelActual: Int, puntosRestantes: Int): Boolean {
    return when {
        nivelActual == 0 -> puntosRestantes >= 1 // Nivel 1 cuesta 1 punto
        nivelActual == 1 -> puntosRestantes >= 1 // Nivel 2 cuesta 1 punto
        nivelActual == 2 -> puntosRestantes >= 1 // Nivel 3 cuesta 1 punto
        else -> false
    }
}

// Confirmar la distribución
fun confirmarDistribucion(niveles: List<Int>, habilidades: List<String>) {
    habilidades.forEachIndexed { index, habilidad ->
        Log.d("Polifacético", "Habilidad: $habilidad, Nivel: ${niveles[index]}")
    }
}

