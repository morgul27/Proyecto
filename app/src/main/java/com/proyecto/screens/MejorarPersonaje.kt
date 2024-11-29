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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
fun MejorarPersonaje(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
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
                                    text = "Vástagos",
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
                    val state = viewModel.state
                    state.experiencia = sharedViewModel.vasExp.value ?: 0
                    MejorarPersonajeBodyContent(
                        navController,
                        viewModel,
                        sharedViewModel,
                        state.experiencia!!
                    )
                }
            }
        )
    }
}


@Composable
fun MejorarPersonajeBodyContent(
    navController: NavController,
    viewModel: MPViewModel,
    sharedViewModel: SharedViewModel,
    exp: Int
){

    val state = viewModel.state
    state.nombreVas = sharedViewModel.vasName.value ?: ""
    state.clanVas = sharedViewModel.vasClan.value ?: ""
    state.generacion = sharedViewModel.vasGeneracion.value ?: 9
    //state.fkvas_usu = sharedViewModel.VasName.value ?: ""
    state.fkvas_clan = sharedViewModel.fkClan.value ?: 9

    viewModel.state.fuerza = sharedViewModel.vasFuerza.value ?: 0
    viewModel.state.destreza = sharedViewModel.vasDestr.value ?: 0
    viewModel.state.resistencia = sharedViewModel.vasResist.value ?: 0
    viewModel.state.carisma = sharedViewModel.vasCarisma.value ?: 0
    viewModel.state.manipulacion = sharedViewModel.vasMan.value ?: 0
    viewModel.state.compostura = sharedViewModel.vasCompostura.value ?: 0
    viewModel.state.inteligencia = sharedViewModel.vasIntel.value ?: 0
    viewModel.state.astucia = sharedViewModel.vasAstucia.value ?: 0
    viewModel.state.resolucion =  sharedViewModel.vasResolucion.value ?: 0



    // Función para actualizar los valores de los atributos en el estado
    fun updateState(atributo: String, nuevoValor: Int, exp3: Int) {
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
            "resolución" -> viewModel.state.resolucion = nuevoValor
            else -> Log.e("MDF2BodyContent", "Atributo desconocido: $atributo")
        }
        state.experiencia = exp3
        Log.d("MIRA AQUI", "expereciaaaaaaaaaaaa: ${state.experiencia}")
        Log.d("MIRA AQUI", "exp3: ${exp3}")
    }



    //prueba
    val atributos = remember { mutableStateListOf(
        "Fuerza",
        "Destreza",
        "Resistencia",
        "Carisma",
        "Manipulación",
        "Compostura",
        "Inteligencia",
        "Astucia",
        "resolución"
    ) }

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
            "resolución"
        )
    }

    val nombreVas = sharedViewModel.vasName.value ?: ""
    val id = sharedViewModel.vasId.value ?: 0
    val clan = sharedViewModel.vasClan.value ?: ""
    val generacion = sharedViewModel.vasGeneracion.value ?: 0


    var puntos = remember { mutableStateListOf(
        sharedViewModel.vasFuerza.value ?: 0,
        sharedViewModel.vasDestr.value ?: 0,
        sharedViewModel.vasResist.value ?: 0,
        sharedViewModel.vasCarisma.value ?: 0,
        sharedViewModel.vasMan.value ?: 0,
        sharedViewModel.vasCompostura.value ?: 0,
        sharedViewModel.vasIntel.value ?: 0,
        sharedViewModel.vasAstucia.value ?: 0,
        sharedViewModel.vasResolucion.value ?: 0,)
    }
    var exp2 by remember { mutableStateOf(exp) }


    // Pantalla visualmente
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //probar si funciona
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Detalles:")
                Text(text = "Nombre: $nombreVas")
                Text(text = "Clan: $clan")
                Text(text = "Generación: $generacion")
                // Continúa mostrando otros detalles del vástago
            }
        }

        // Espacio adicional
        item {
            Spacer(modifier = Modifier.height(25.dp))
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Box(Modifier.size(200.dp, 45.dp)) {
                    Text(
                        "Experiencia",
                        Modifier.align(Alignment.CenterStart),
                        fontSize = 20.sp,
                    )
                }

                Box (Modifier.size(180.dp, 45.dp)) {
                    Button(
                        onClick = {
                            if(exp2 > 0) {
                                exp2 -= 1
                                state.experiencia = exp2
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

                    Text(text = "${exp2}",
                        Modifier.align(Alignment.Center)
                    )

                    Button(onClick = {
                        exp2 += 1
                        state.experiencia = exp2
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

        item {
            Spacer(modifier = Modifier.height(25.dp))
        }

        //Puntos
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

                    Box (Modifier.size(180.dp, 45.dp)) {
                        Button(onClick = {
                            val expCalculada = exp2 + calculo(puntos[index])
                            if (puntos[index] > 0) {
                                puntos[index] -= 1
                                exp2 = expCalculada
                                updateState(atributos[index], puntos[index], exp2)
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

                        Text(text = "${puntos[index]}",
                            Modifier.align(Alignment.Center)
                        )

                        Button(onClick = {
                            val resto = calculo(puntos[index] + 1)
                            val expCalculada = exp2 - calculo(puntos[index] + 1)
                            if (puntos[index] < 6 && resto <= exp2) {
                                puntos[index] += 1
                                exp2 = expCalculada
                                updateState(atributos[index], puntos[index], exp2)
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


        //FIN

        // Botón de guardar
        item {
            Spacer(modifier = Modifier.height(25.dp))
            DefaultButton(onClick = {
                state.fuerza_voluntad = state.resolucion?.let { state.compostura?.plus(it) }
                state.salud = state.resistencia?.plus(3)
                viewModel.UpdateVastago(sharedViewModel.vasId.value ?: 0)
                navController.navigate(route = Screens.MenuPrincipal.route)
            },
                text = ("Guardar")
            )
        }

        // Botón de volver
        item {
            DefaultButton(onClick = { navController.popBackStack() },
                text = ("Volver")
            )
            Spacer(modifier = Modifier.height(20.dp))
            DefaultButton(onClick = {
                navController.popBackStack()
                sharedViewModel.vasId.value?.let { viewModel.eliminarVasPorId(it) }
            },
                text = ("Borrar vastago")
            )
        }

        // Espacio adicional debajo de los elementos
        item {
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


@Composable
fun ExplicacionMejora() {
    var showDialog by remember { mutableStateOf(false) }

    // Botón para mostrar el diálogo
    Button(onClick = { showDialog = true }) {
        Text("Mostrar Diálogo")
    }

    // Diálogo
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Título del Diálogo") },
            text = { Text("Este es el contenido del diálogo.") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

}
