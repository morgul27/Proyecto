package com.proyecto.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.draw.shadow
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
                        CFBody(navController, viewModel, sharedViewModel)

                    }
                }
            }
        )
    }
}


@Composable
fun CFBody(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel) {
    val state = viewModel.state
    var dropdownExpanded by remember { mutableStateOf(false) }
    var seleccPoder by remember { mutableStateOf("Selecciona un Poder") }



    LaunchedEffect(Unit) {
        viewModel.ObtenerPoderes(1)
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

            Log.d("MIRA", "A: ")
            //prueba Clan
            Text("Prueba Poderes")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
                    .shadow(
                        elevation = 6.dp, // Tamaño de la sombra
                        shape = RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp
                        ), // Esquinas redondeadas
                        clip = false // La sombra no recorta el contenido
                    )
                    .background(
                        color = Color(0xFFE8E6F8),
                        shape = RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomStart = 1.dp,
                            bottomEnd = 1.dp
                        )
                    )
                    .height(65.dp),
                contentAlignment = Alignment.CenterStart // Alineación del contenido dentro del Box
            ) {

                // Botón que abre el menú desplegable
                TextButton(
                    onClick = { dropdownExpanded = true }, // Abrir menú
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        seleccPoder,
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontFamily = ghoticFamily
                    ) // Mostrar la opción seleccionada
                }

                // Menú desplegable
                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = {
                        dropdownExpanded = false
                    } // Cerrar el menú al hacer clic fuera
                ) {
                    // Opciones del menú
                    state.listaPoderes.forEach { poderes ->
                        DropdownMenuItem(
                            onClick = {
                                seleccPoder = poderes
                                dropdownExpanded = false
                            },
                            text = {
                                Text(
                                    poderes,
                                    color = Color.Black
                                )
                            }
                        )
                        Log.d("MIRA AQUI", "Poderes: $poderes")
                    }
                }
            }
        }
    }
}