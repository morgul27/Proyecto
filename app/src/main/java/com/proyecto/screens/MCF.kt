package com.proyecto.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.MPViewModel
import com.proyecto.R
import com.proyecto.SharedViewModel
import com.proyecto.bbdd.entity.Clan
import com.proyecto.navigation.Screens
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.DefaultButton
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import com.proyecto.ui.theme.ghoticFamily
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MCF(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
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
                        MDFBodyContent(navController, viewModel, sharedViewModel)

                    }
                }
            }
        )
    }
}

@Composable
fun MDFBodyContent(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    val state = viewModel.state
    var nombreVas by remember { mutableStateOf(viewModel.state.nombreVas) }
    var idClan by remember { mutableStateOf(1) }
    val maxChar = 5

    LaunchedEffect(state.clanVas) {
        idClan = viewModel.obtenerIdPorNombreClan(state.clanVas ?: "Nosferatu")
    }

    // Variable para controlar si el menú desplegable está expandido
    var dropdownExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
       viewModel.obtenerClanes()
    }

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
            Text("Rellena los campos")

            Spacer(modifier = Modifier.height(25.dp))

            //Creacion del personaje rellenando
            Text("Nombre")
            nombreVas?.let {
                TextField(
                    value = it,
                    onValueChange = { if (it.length <= maxChar) nombreVas = it },
                    textStyle = Typography.bodyMedium
                )
            }


            Spacer(modifier = Modifier.height(25.dp))
            
            //Menu desplegable
            Text("Clan")

            // Mostrar el menú desplegable de clanes
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(onClick = { dropdownExpanded = true })
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = if (state.clanVas.isNullOrEmpty()) "Selecciona un clan" else state.clanVas!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f) // Hace que el Text ocupe el espacio disponible
                            .padding(start = 45.dp)
                    )
                    IconButton(onClick = { dropdownExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
                    }
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            top = 48.dp,
                            end = 20.dp
                        ) // Ajuste vertical para colocar el menú debajo del texto
                ) {
                    DropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }
                    ) {
                        state.nombreClanes.forEach { clan ->
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.setClan(clan.nombreClan)
                                    dropdownExpanded = false
                                },
                                text = {
                                    Text(
                                        clan.nombreClan,
                                        color = Color.Black
                                    )
                                }
                            )
                        }
                    }
                }
            }



            Spacer(modifier = Modifier.height(25.dp))
            Text("Generación")
            TextField(
                value = state.generacion?.toString()
                    ?: "", // Convertir el Int a String o usar una cadena vacía si es nulo
                onValueChange = {
                    // Convertir el valor de String a Int y pasar a la función setGeneracion en el ViewModel
                    viewModel.setGeneracion(
                        it.toIntOrNull() ?: 0
                    ) // Si la conversión falla, usa 0 como valor predeterminado
                },
                textStyle = Typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(25.dp))

            if(state.generacion != null && state.nombreVas != null && state.clanVas != null){
                DefaultButton(onClick = {
                    viewModel.setFkvas_clan(idClan) // Ahora idClan se actualizará antes de ser utilizado
                    nombreVas?.let { viewModel.setNombreVas(it) }
                    navController.navigate(route = Screens.MCF2.route)},
                    text ="Siguiente"
                )
            }
            else{
                DefaultButton(onClick = {
                    viewModel.setFkvas_clan(idClan) // Ahora idClan se actualizará antes de ser utilizado
                    nombreVas?.let { viewModel.setNombreVas(it) }},
                    text ="Rellena el resto de campos",
                    containerColor = Borgoña.copy(alpha = 0.5f)
                )
            }


            //otra cosa
            DefaultButton(onClick = { navController.popBackStack() },
                text = "Volver"
            )

            // Mostrar el segundo menú
            DefaultButton(
                onClick = { viewModel.toggleSecondMenu() },
                modifier = Modifier.padding(16.dp),
                text = "Leyenda"
            )
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

@Composable
fun MDFSecondBodyContent(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Text(stringResource(R.string.generacionExpl))
            Spacer(modifier = Modifier.height(25.dp))

            Text(stringResource(R.string.clanNosfExpl))
            Spacer(modifier = Modifier.height(25.dp))

            Text(stringResource(R.string.clanGangrExpl))
            Spacer(modifier = Modifier.height(25.dp))

            Text(stringResource(R.string.clanBrujahExpl))
            Spacer(modifier = Modifier.height(25.dp))


            //boton para ir al menu anterior
            DefaultButton(
                onClick = { viewModel.toggleSecondMenu() },
                modifier = Modifier.padding(16.dp),
                text = "Volver"
            )
        }
    }
}



@Preview
@Composable
fun prev(){

    //prueba
    val items = listOf("Item 1", "Item 2", "Item 3")
    val selectedItem = remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Text("Clan")
    // Mostrar el menú desplegable de clanes
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Primero", // Mostrar el clan seleccionado o un mensaje predeterminado
            modifier = Modifier
                .clickable(onClick = {
                    expanded = !expanded
                }) // Invertir el estado de dropdownExpanded al hacer clic
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { clan ->
                DropdownMenuItem(
                    onClick = {
                        selectedItem
                    },
                    text = {Text("Segundo")} // Pasar el nombre del clan como texto del elemento del menú
                )

            }
        }
    }
}
