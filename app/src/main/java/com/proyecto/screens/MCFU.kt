package com.proyecto.screens

import android.annotation.SuppressLint
import android.icu.text.ListFormatter
import android.text.style.BackgroundColorSpan
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
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
fun MCFU(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel) {
    var exp by remember { mutableStateOf(0) }
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
                    val state = viewModel.state
                    val listExp =
                        remember { mutableStateListOf(0, 9, 8, 7, 6, 5, 4, 4, 3, 3, 2, 2, 1, 1) }
                    var expGeneracion by remember {
                        mutableStateOf(state.generacion?.let { it1 -> listExp.getOrElse(it1) { 0 } })
                    }
                    //este es el calculo de N * 15 + 5 * (N - 1)
                    val expCalculo = remember {
                        mutableStateOf(
                            (expGeneracion?.times(15) ?: 0) + 5 * (expGeneracion?.minus(1) ?: 0)
                        )
                    }
                    if (expCalculo.value < 0) {
                        expCalculo.value = 0
                    }


                    // Actualizar el estado de exp cuando cambie expCalculo
                    exp = expCalculo.value
                    state.experiencia = exp


                    //llamada a MCF2Body
                    exp.let { it1 ->
                        MCFUBody(navController, viewModel, sharedViewModel, it1)
                    }
                }
            }
        )
    }
}

@Composable
fun MCFUBody(
    navController: NavController,
    viewModel: MPViewModel,
    sharedViewModel: SharedViewModel,
    exp: Int
) {
    val state = viewModel.state
    var puntosPorDisciplina by remember { mutableStateOf(0) }
    val poderesPorDisciplina = List(puntosPorDisciplina) { "" }
    Log.i("nivel disc 1", "${state.listaNivelDisciplinas[0]}")

    //prueba
    val poderesSeleccionados = remember { mutableStateMapOf<Int, String>() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        item {
            //espacio para no agobiar
            Spacer(modifier = Modifier.height(45.dp))

            state.listaNivelDisciplinas.forEachIndexed { index, _ ->
                Text(text = "Poderes de ${state.listaDisciplinasPorClan[index]}")
                Spacer(modifier = Modifier.height(5.dp))
                for (i in 1..state.listaNivelDisciplinas[index]) {
                    DropdownMPoder(
                        index + 1,
                        viewModel,
                        state.listaIdDisciplinas[index],
                        poderesSeleccionados
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Spacer(modifier = Modifier.height(35.dp))
            }
        }
        item {
            DefaultButton(
                onClick = {
                    // Llamar al metodo para guardar los poderes seleccionados
                    poderesSeleccionados.forEach { (fkDisciplinas, nombre) ->
                        viewModel.guardarPoderes(nombre, fkDisciplinas)
                    }
                        navController.navigate(route = Screens.MenuPrincipal.route)
                },
                text = "GUARDAR"
            )
        }
    }
}

@Composable
fun DropdownMPoder(numero: Int,
                   viewModel: MPViewModel,
                   idDisciplina: Int,
                   poderesSeleccionados: MutableMap<Int, String>
) {
    val state = viewModel.state
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Seleccionar Poder $numero") }

    var idTablaDisc by remember { mutableStateOf(0) }
    var listaPoderes by remember { mutableStateOf<List<String>>(emptyList()) }

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


//Calcular la experiencia
private fun calculo(valorAnt: Int): Int {
    var cal: Int
    cal = valorAnt * 5
    return cal
}








