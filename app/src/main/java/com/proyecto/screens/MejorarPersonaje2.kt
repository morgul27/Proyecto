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
fun MejorarPersonaje2(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
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
                    state.experiencia = sharedViewModel.vasExp.value ?: 9999
                    MejorarPersonajeBody(
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
fun MejorarPersonajeBody(
    navController: NavController,
    viewModel: MPViewModel,
    shared: SharedViewModel,
    exp: Int
) {
    val state = viewModel.state
    val listaNivel = remember { mutableStateListOf(*state.listaNivelDisciplinas.toTypedArray()) }
    val listDisc = state.listaDisciplinasPorClan
    var exp2 by remember { mutableStateOf(exp) }
    val numerosMult = listOf(5, 3)

    Log.i("lista Niveles","${state.listaDisciplinasPorClan}")
    Log.i("listDisc","[${listDisc[0]}, ${listDisc[1]}, ${listDisc[2]}]")


    //interfaz de usuario
    //experiencia en pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 25.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(25.dp))
        Text("Exp: ${exp2}")
        Spacer(modifier = Modifier.height(25.dp))
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //MEJORA DE DISCIPLINAS
        item {
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "DISCIPLINAS")
            }
        }
        listDisc.forEachIndexed { index, _ ->
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        Modifier
                            .width(200.dp)
                            .heightIn(min = 45.dp, max = 80.dp)
                    ) {
                        explicacion(
                            texto = listDisc[index],
                            textoT = "Disciplinas[index]",
                            textoExpl = "textoExplicacion[index]",
                            Modifier.align(Alignment.CenterStart),
                            fontSize = 20.sp,
                        )
                    }
                    Box(Modifier.size(180.dp, 45.dp)) {
                        Button(
                            onClick = {
                                val expCalculada = exp2 + calculo(listaNivel[index], numerosMult[0])
                                if (listaNivel[index] > 0) {
                                    listaNivel[index] -= 1
                                    exp2 = expCalculada
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
                            text = "${listaNivel[index]}",
                            Modifier.align(Alignment.Center)
                        )

                        Button(
                            onClick = {
                                val resto = calculo(listaNivel[index] + 1, numerosMult[0])
                                val expCalculada =
                                    exp2 - calculo(listaNivel[index] + 1, numerosMult[0])
                                if (listaNivel[index] < 6 && resto <= exp2) {
                                    listaNivel[index] += 1
                                    exp2 = expCalculada
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
        // Botón de guardar
        item {
            Spacer(modifier = Modifier.height(25.dp))
            DefaultButton(onClick = {
                state.listaNivelDisciplinas.clear()
                state.listaNivelDisciplinas.addAll(listaNivel)
                Log.i("lista1:","${state.listaNivelDisciplinas[0]}")
                state.experiencia = exp2
                Log.i("exp state:","${state.experiencia}")
                shared.vasExp.value = exp2


                navController.navigate(route = Screens.MenuPrincipal.route)
            },
                text = ("Guardar")
            )
        }


    }
}

//Calcular la experiencia
private fun calculo(valorAnt: Int, mult: Int): Int {
    var cal: Int
    cal = valorAnt * mult
    return cal
}