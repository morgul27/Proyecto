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
import com.proyecto.bbdd.entity.DisciplinasVas
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

    var puntosT = remember { mutableStateOf(29) }

    state.fkvas_clan?.let { viewModel.getDisciplinasPorClan(it) }


    //state.listaDisciplinasPorClan[0]

    Log.d("Disciplina", "1: ${state.listaDisciplinasPorClan[0]}")
    Log.d("Disciplina", "2: ${state.listaDisciplinasPorClan[1]}")
    Log.d("Disciplina", "3: ${state.listaDisciplinasPorClan[2]}")
    Log.d("fkvas_clan", "clan: ${state.fkvas_clan}")

    var puntos = remember {
        mutableStateListOf(
            0, 0, 0
        )
    }
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
                    Box(Modifier
                        .width(200.dp)
                        .heightIn(min = 45.dp, max = 80.dp)
                    ) {
                        explicacion(
                            texto = state.listaDisciplinasPorClan[index],
                            textoT = "abilidades[index]",
                            textoExpl = "textoExplicacion[index]",
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
                    navController.navigate(route = Screens.MenuPrincipal.route)
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
