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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch

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
                                IconButton(onClick = { navController.navigate(route = Screens.MenuFicha.route) }) {
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

                    sharedViewModel.fkClan.value.let { viewModel.getDisciplinasPorClan(it) }
                    sharedViewModel.fkClan.value.let { viewModel.getIdDisciplinasPorClan(it) }

                    LaunchedEffect(Unit) {
                        viewModel.state.listaNivelDisciplinas =
                            viewModel.obtenernivelesDisciplinas(
                                sharedViewModel.vasId.value ?: 1) as MutableList
                    }

                    var exp = sharedViewModel.vasExp.value ?: 1111
                    CFBody(navController, viewModel, sharedViewModel, exp)
                }
            }
        )
    }
}


@Composable
fun CFBody(navController: NavController, viewModel: MPViewModel, shared: SharedViewModel, exp: Int) {
    val state = viewModel.state
    Log.i("listaDisc","${state.listaDisciplinasPorClan}")
    Log.i("listaNivel","${state.listaNivelDisciplinas}")
    val listDisc = state.listaDisciplinasPorClan

    var list = mutableListOf(0, 0, 0)


    var exp2 by remember { mutableStateOf(exp) }
    val listaNivel = remember { mutableStateListOf(*list.toTypedArray()) }

    var listaNivel2 =  remember { mutableStateListOf(state.listaNivelDisciplinas) }
    val numerosMult = listOf(5, 3)

    Log.i("lista N 2","${listaNivel2[0]}")
    val nombreVas = shared.vasName.value ?: ""
    val id = shared.vasId.value ?: 0
    val clan = shared.vasClan.value ?: ""
    val generacion = shared.vasGeneracion.value

    //variables para mejoras de Atributos
    val atributos = listOf(
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
    val textoExplicacionA = listOf(
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
    val puntosA = remember { mutableStateListOf(
        shared.vasFuerza.value,
        shared.vasDestr.value,
        shared.vasResist.value,
        shared.vasCarisma.value,
        shared.vasMan.value,
        shared.vasCompostura.value,
        shared.vasIntel.value,
        shared.vasAstucia.value,
        shared.vasResolucion.value,)
    }

    //Mejoras para las Habilidades
    val habilidades = listOf(
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

    val textoExplicacionH = listOf(
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
            "Tecnología"
        )

    val puntosH = remember { mutableStateListOf(
        //columna 1
        shared.armas_de_fuego.value,
        shared.artesania.value,
        shared.atletismo.value,
        shared.conducir.value,
        shared.latrocinio.value,
        shared.pelea.value,
        shared.pelea_con_armas.value,
        shared.sigilo.value,
        shared.superviviencia.value,
        //columna 2
        shared.callejeo.value,
        shared.etiqueta.value,
        shared.interpretacion.value,
        shared.intimidacion.value,
        shared.liderazgo.value,
        shared.perspicacia.value,
        shared.persuasion.value,
        shared.subterfugio.value,
        shared.trato_con_animales.value,
        //columna 3
        shared.academicismo.value,
        shared.ciencias.value,
        shared.consciencia.value,
        shared.finanzas.value,
        shared.investigacion.value,
        shared.medicina.value,
        shared.ocultismo.value,
        shared.politica.value,
        shared.tecnologia.value
    ) }




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

        //Boton de experiencia
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

        //Puntos Atributos
        puntosA.forEachIndexed { index, _ ->
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
                            textoExpl = textoExplicacionA[index],
                            Modifier.align(Alignment.CenterStart),
                            fontSize = 20.sp,
                        )
                    }

                    Box (Modifier.size(180.dp, 45.dp)) {
                        Button(onClick = {
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

                        Text(text = "${puntosA[index]}",
                            Modifier.align(Alignment.Center)
                        )

                        Button(onClick = {
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
        //lista de Habilidaes
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
        listDisc.forEachIndexed { index, _ ->
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


        //FIN

        // Botón de guardar
        item {
            Spacer(modifier = Modifier.height(25.dp))
            DefaultButton(onClick = {
//                state.listaNivelDisciplinas.clear()
//                state.listaNivelDisciplinas.addAll(listaNivel)
                Log.i("lista1:","${state.listaNivelDisciplinas[0]}")
                state.experiencia = exp2
                Log.i("exp state:","${state.experiencia}")
                shared.vasExp.value = exp2

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

                navController.navigate(route = Screens.MenuPrincipal.route)
            },
                text = ("Guardar")
            )
        }

        // Botón de volver
        item {
            DefaultButton(onClick = { navController.navigate(route = Screens.MenuFicha.route) },
                text = ("Volver")
            )
            Spacer(modifier = Modifier.height(20.dp))
            DefaultButton(onClick = {
                shared.vasId.value?.let { viewModel.eliminarVasPorId(it) }
                navController.navigate(route = Screens.MenuFicha.route)
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
private fun calculo(valorAnt: Int, mult: Int): Int {
    var cal: Int
    cal = valorAnt * mult
    return cal
}



