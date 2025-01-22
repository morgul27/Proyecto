package com.proyecto.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
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
import java.util.Locale.filter

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
    state.id = shared.vasId.value
    var pode: List<String> = emptyList()

//    Log.i("lista Niveles","${state.listaDisciplinasPorClan}")
//    Log.i("listDisc","[${listDisc[0]}, ${listDisc[1]}, ${listDisc[2]}]")
//    Log.e("____Obtenidos", "${viewModel.poderesObtenidos[viewModel.listaIdPoder[0]]}")


    val poderesSeleccionados = remember { mutableStateMapOf<Int, String>() }

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
            .padding(top = 75.dp),
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


        //ultima lista PODERES DISCIPLINAS
        //texto
        item {
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "PODERES DISCIPLINAS")
            }
        }
        item {
            Spacer(modifier = Modifier.height(25.dp))
            state.listaNivelDisciplinas.forEachIndexed { index, _ ->
                Text(text = "Poderes de ${state.listaDisciplinasPorClan[index]}")
                Spacer(modifier = Modifier.height(5.dp))

                // Crear una columna (Column) para cada disciplina
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Iterar sobre los niveles de disciplinas (puedes tener más de una celda por disciplina)
                    for (i in 1..state.listaNivelDisciplinas[index]) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(4.dp)
                                .border(1.dp, Color.Black)
                                .background(Borgoña)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Log.i("index?","${index}")
                            Log.i("index-1?","${index-1}")
                            Log.w("indice lista?","${viewModel.listaFKDiscVas.indices}")
                            Log.w("listaFK?","${viewModel.listaFKDiscVas}")
                            Log.d("poder?","${viewModel.poderesObtenidos2}")
                            pode = if (index in viewModel.listaFKDiscVas.indices) {
                                Log.e("pass","?: passa")
                                viewModel.poderesObtenidos2
                                    .filter { it.fk_disciplinas == viewModel.listaFKDiscVas[index] }
                                    .map { it.nombre }
                            } else {
                                Log.e("Npass","?: no passa")
                                emptyList()
                            }
                            Log.e("poe","?: ${pode}")
                            DropdownMPoder(
                                index + 1,
                                viewModel,
                                state.listaIdDisciplinas[index],
                                poderesSeleccionados,
                                i,
                                pode
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
                Spacer(modifier = Modifier.height(35.dp))
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

                Log.w("nivelList","${listaNivel[0]}")
                //LLamada a actualizacion de vastago
                shared.ActualizarVastagoConDisciplinasSH(
                    puntos = listaNivel,
                    listaIdDisciplinas = state.listaIdDisciplinas,
                    viewModel
                ) {}

                //guardar y actualizar poderes seleccionados
                Log.i("Lista Poderes","${poderesSeleccionados}")

                var i = 0
                poderesSeleccionados.forEach {(id, nombre) ->
                    Log.w("id y nombre:","${id}, fk: ${nombre}")
                    viewModel.actualizarPoder(id, nombre, state.listaIdDisciplinas[i])
                    i += 1
                }

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

@Composable
private fun DropdownMPoder(
    numero: Int,
    viewModel: MPViewModel,
    idDisciplina: Int,
    poderesSeleccionados: MutableMap<Int, String>,
    i: Int,
    poderesVas: List<String>
) {
    val state = viewModel.state
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(
        if ((i-1) in poderesVas.indices) poderesVas[i - 1]
        else "Seleccionar Poder $numero"
    ) }

    var idTablaDisc by remember { mutableStateOf(0) }
    var listaPoderes by remember { mutableStateOf<List<String>>(emptyList()) }


    poderesVas.forEach { nombre ->
        Log.w("PoderesVas2", "N: ${nombre}")

    }
//    Log.i("FK", "a: ${viewModel.listaFKDiscVas}")
//    Log.i("Obtenidos", "O: ${viewModel.poderesObtenidos2}")
//    Log.w("Indice", "I: ${poderesVas.indices}")
//    Log.w("numero", "Disc: ${numero}")
//    Log.e("vuelta", "eltas: ${i}")

    LaunchedEffect(Unit) {
        listaPoderes = state.id?.let { viewModel.ObtenerPoderes(it, idDisciplina) }!!
        idTablaDisc = viewModel.obteneridDisciplina(idDisciplina, state.id!!)
    }


    Column {
        Text(
            text = selectedText,
            modifier = Modifier.clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listaPoderes.forEach { opcion ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = opcion,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontFamily = ghoticFamily
                        )
                    },
                    onClick = {
                        selectedText = opcion
                        expanded = false

                        // Guardar la selección en el estado temporal
                        poderesSeleccionados[idTablaDisc] = opcion
                    }
                )
            }
        }
    }
}
