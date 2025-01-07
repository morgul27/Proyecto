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
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.runtime.State
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
                    val exp = state.experiencia

                    //llamada a MCF2Body
                    exp.let { it1 ->
                        if (it1 != null) {
                            MCFUBody(navController, viewModel, sharedViewModel, it1)
                        }
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
    Log.i("nivel disc 1", "${state.listaNivelDisciplinas[0]}")
    //variable de poderes
    val poderesSeleccionados = remember { mutableStateMapOf<Int, String>() }
    var exp2 by remember { mutableStateOf(exp) }
    val numerosMult = listOf(5, 3)

    //lista disciplinas
    val listaNivel = remember { mutableStateListOf(*state.listaNivelDisciplinas.toTypedArray()) }


    //variables para mejoras de Atributos
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
    val textoExplicacionA = remember {
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
    var puntosA = remember { mutableStateListOf(
        state.fuerza,
        state.destreza,
        state.resistencia,
        state.carisma,
        state.manipulacion,
        state.compostura,
        state.inteligencia,
        state.astucia,
        state.resolucion
    ) }

    //Mejoras para las Habilidades
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

    val textoExplicacionH = remember {
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
    var puntosH = remember { mutableStateListOf(
        //columna 1
        state.armas_de_fuego,
        state.artesania,
        state.atletismo,
        state.conducir,
        state.latrocinio,
        state.pelea,
        state.pelea_con_armas,
        state.sigilo,
        state.superviviencia,
        //columna 2
        state.callejeo,
        state.etiqueta,
        state.interpretacion,
        state.intimidacion,
        state.liderazgo,
        state.perspicacia,
        state.persuasion,
        state.subterfugio,
        state.trato_con_animales,
        //columna 3
        state.academicismo,
        state.ciencias,
        state.consciencia,
        state.finanzas,
        state.investigacion,
        state.medicina,
        state.ocultismo,
        state.politica,
        state.tecnologia
    ) }




    //interfaz de usuario
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
        item{
            //espacio para no agobiar
            Spacer(modifier = Modifier.height(45.dp))
        }
        //MEJORA ATRIBUTOS
        item{
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "ATRIBUTOS")
            }
            //Botones
            puntosA.forEachIndexed { index, _ ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(Modifier.size(200.dp, 45.dp)) {
                        explicacion(
                            texto = atributos[index],
                            textoT = atributos[index],
                            textoExpl = textoExplicacionA[index],
                            Modifier.align(Alignment.CenterStart),
                            fontSize = 20.sp,
                        )
                    }
                    //Modifier
                    Box(Modifier.size(180.dp, 45.dp)) {
                        Button(
                            onClick = {
                                val expCalculada = exp2 + calculo(puntosA[index], numerosMult[0])
                                if (puntosA[index] > 0) {
                                    puntosA[index] -= 1
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
                            text = "${puntosA[index]}",
                            Modifier.align(Alignment.Center)
                        )

                        Button(
                            onClick = {
                                val resto = calculo(puntosA[index] + 1, numerosMult[0])
                                val expCalculada = exp2 - calculo(puntosA[index] + 1, numerosMult[0])
                                if (puntosA[index] < 6 && resto <= exp2) {
                                    puntosA[index] += 1
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

                }
            }
        }
        //MEJORAS HABILIDADES
        item {
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "HABILIDADES")
            }
            //lista Habilidades
            //Botones
            puntosH.forEachIndexed { index, _ ->
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
                            texto = habilidades[index],
                            textoT = habilidades[index],
                            textoExpl = textoExplicacionH[index],
                            Modifier.align(Alignment.CenterStart),
                            fontSize = 20.sp,
                        )
                    }
                    Box(Modifier.size(180.dp, 45.dp)) {
                        Button(
                            onClick = {
                                val expCalculada = exp2 + calculo(puntosH[index], numerosMult[1])
                                if (puntosH[index] > 0) {
                                    puntosH[index] -= 1
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
                            text = "${puntosH[index]}",
                            Modifier.align(Alignment.Center)
                        )

                        Button(
                            onClick = {
                                val resto = calculo(puntosH[index] + 1, numerosMult[1])
                                val expCalculada = exp2 - calculo(puntosH[index] + 1, numerosMult[1])
                                if (puntosH[index] < 6 && resto <= exp2) {
                                    puntosH[index] += 1
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
        //MEJORA DE DISCIPLINAS
        //texto
        item {
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "DISCIPLINAS")
            }
        }
        state.listaDisciplinasPorClan.forEachIndexed { index, _ ->
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
                                val expCalculada = exp2 - calculo(listaNivel[index] + 1, numerosMult[0])
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

        //Boton para salir
        item {
            DefaultButton(
                onClick = {
                    state.listaNivelDisciplinas.clear()
                    state.listaNivelDisciplinas.addAll(listaNivel)
                    Log.i("lista1:","${state.listaNivelDisciplinas[0]}")
                    state.experiencia = exp2
                    Log.i("exp state:","${state.experiencia}")
                    //prueba 1 atributos
                    atributos.forEachIndexed { index, atributo ->
                        // Llama a la función para actualizar el estado
                        updateAtributos(atributo, puntosA[index], viewModel)
                    }
                    //actualizar Habilidades
                    habilidades.forEachIndexed { index, habilidad ->
                        // Llama a la función para actualizar el estado
                        updateHabilidades(habilidad, puntosH[index], viewModel)
                    }
                    state.fuerza_voluntad = state.resolucion.let { state.compostura.plus(it) }
                    state.salud = state.resistencia.plus(3)


                    //LLamada a actualizacion de vastago
                    viewModel.ActualizarVastagoConDisciplinas(
                        puntos = listaNivel,
                        listaIdDisciplinas = state.listaIdDisciplinas
                    ) {}

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
private fun calculo(valorAnt: Int, mult: Int): Int {
    var cal: Int
    cal = valorAnt * mult
    return cal
}









