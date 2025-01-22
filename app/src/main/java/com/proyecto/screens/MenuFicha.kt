package com.proyecto.screens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.MPViewModel
import com.proyecto.R
import com.proyecto.SharedViewModel
import com.proyecto.navigation.Screens
import com.proyecto.ui.theme.Blanco
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.DefaultButton
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import com.proyecto.ui.theme.ghoticFamily

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MenuFicha(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    val image = painterResource(R.drawable.marmol)
    ProyectoTheme {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme,
            typography = Typography,
            content = {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.fillMaxWidth(),
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent,
                                titleContentColor = Borgoña,
                                navigationIconContentColor = Borgoña
                            ),
                            navigationIcon = {
                                IconButton(onClick = { navController.navigate(route = Screens.MenuPrincipal.route) }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Arrow back"
                                    )
                                }
                            },
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
                            }
                        )
                    }
                ){
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
                    state.clanVas = null
                    state.nombreVas = ""
                    state.generacion = null
                    state.fuerza = 0
                    state.destreza = 0
                    state.resistencia = 0
                    state.carisma = 0
                    state.manipulacion = 0
                    state.compostura = 0
                    state.inteligencia = 0
                    state.astucia = 0
                    state.resolucion = 0
                    state.experiencia = 0

                    BodyFicha(navController, viewModel, sharedViewModel)
                }
            }
        )
    }
}

@Composable
fun BodyFicha(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    val state = viewModel.state
    val simboloNosf = painterResource(R.drawable.nosferatu_symbol)
    val simboloBr = painterResource(R.drawable.brujah_symbol)
    val simboloGang = painterResource(R.drawable.gangrel_symbol)

    val vastagos = sharedViewModel.listaVastagos
    val idUsu = state.fkvas_usu

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            Spacer(modifier = Modifier.height(25.dp))
            // Mostrar un indicador de carga si está en progreso
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                // Intentar obtener las fichas de personajes
                LaunchedEffect(Unit) {
                    viewModel.obtenerVastagosFk(idUsu ?: 1)
                }
                // Mostrar un mensaje si no hay fichas de personajes
                if (state.nombresVas.isEmpty()) {
                    Text(text = "No existe ficha",
                        color = Blanco)
                } else {
                    // Mostrar las fichas de personajes si están disponibles
                    if(viewModel.direccionPDF.value == true) {
                        Text(text = "Pulsa para crear un PDF de un vástago",
                            color = Blanco)
                    }else if (viewModel.direccionHist.value == false){
                        Text(text = "Pulsa para mejorar a un vástago",
                            color = Blanco)
                    }else{
                        Text(text = "Elige un vástago para empezar con la historia",
                            color = Blanco)
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    state.nombresVas.forEach { user ->
                        Card(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .clickable {
                                    user.id?.let {
                                        sharedViewModel.setVastagoInfo(
                                            it,
                                            user.nombreVas,
                                            user.clan,
                                            user.generacion,
                                            //Atributos
                                            user.fuerza,
                                            user.destreza,
                                            user.resistencia,
                                            user.carisma,
                                            user.manipulacion,
                                            user.compostura,
                                            user.inteligencia,
                                            user.astucia,
                                            user.resolucion,
                                            user.salud,
                                            user.fuerza_voluntad,
                                            user.experiencia,
                                            //Habilidades
                                            //Columna 1
                                            user.armas_de_fuego,
                                            user.artesania,
                                            user.atletismo,
                                            user.conducir,
                                            user.latrocinio,
                                            user.pelea,
                                            user.pelea_con_armas,
                                            user.sigilo,
                                            user.superviviencia,
                                            //columna 2
                                            user.callejeo,
                                            user.etiqueta,
                                            user.interpretacion,
                                            user.intimidacion,
                                            user.liderazgo,
                                            user.perspicacia,
                                            user.persuasion,
                                            user.subterfugio,
                                            user.trato_con_animales,
                                            //Columna 3
                                            user.academicismo,
                                            user.ciencias,
                                            user.consciencia,
                                            user.finanzas,
                                            user.investigacion,
                                            user.medicina,
                                            user.ocultismo,
                                            user.politica,
                                            user.tecnologia,
                                            //FKs
                                            user.fkvas_usu,
                                            user.fkvas_clan
                                        )
                                    }
                                    if (viewModel.direccionPDF.value == true) {
                                        navController.navigate(route = Screens.PantallaPDF.route)
                                    } else if (viewModel.direccionHist.value == false) {
                                        navController.navigate(route = Screens.PruebaCF.route)
                                    } else {
                                        navController.navigate(route = Screens.InicioHist.route)
                                    }
                                }
                            ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ){

                                val clanImage = when (user.fkvas_clan) {
                                    1 -> simboloNosf
                                    2 -> simboloGang
                                    3 -> simboloBr
                                    else -> simboloNosf
                                }
                                Image(
                                    painter = clanImage,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .alpha(0.6F)
                                )

                                Column {
                                    Text(
                                        text = "Nombre: ${user.nombreVas}",
                                        fontSize = 15.sp,
                                        color = Color.Black,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.width(150.dp) // Ajusta el ancho según sea necesario
                                    )
                                    Text(text = "Generacion: ${user.generacion}",
                                        fontSize = 15.sp,
                                        color = Color.Black)
                                }
                                Column {
                                    Text(text = "EXP: ${user.experiencia}",
                                        fontSize = 15.sp,
                                        color = Color.Black)
                                    Text(text = "Clan: ${user.clan}",
                                        fontSize = 15.sp,
                                        color = Color.Black)
                                }
                            }
                        }
                    }
                }
                DefaultButton(onClick = { navController.navigate(route = Screens.MCF.route) },
                    "Crear Ficha de personaje"
                )

                DefaultButton(onClick = { navController.navigate(route = Screens.MenuPrincipal.route) },
                "Volver"
                )


                DialogExample()
            }
        }
    }
}


@Composable
fun DialogExample() {
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



