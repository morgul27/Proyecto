package com.proyecto.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.proyecto.MPViewModel
import com.proyecto.MainActivity
import com.proyecto.R
import com.proyecto.SharedViewModel
import com.proyecto.bbdd.UsuarioDatabase
import com.proyecto.bbdd.repository.UsuarioRepository
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
fun MenuPrincipal(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    val nombreUsuario = sharedViewModel.getUserName()
    val image = painterResource(R.drawable.marmol)
    ProyectoTheme {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme,
            typography = Typography,
            content = {
                Scaffold (
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent,
                                titleContentColor = Borgoña,
                                navigationIconContentColor = Borgoña
                            ),
                            navigationIcon = {
                                IconButton(onClick = {navController.navigate(route = Screens.MenuUsuario.route) }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Arrow back"
                                    )
                                }
                            },
                            title = {
                                Text(
                                    text = "Menú Principal",
                                    fontFamily = ghoticFamily,
                                    maxLines = 1,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentSize(Alignment.Center)
                                        .padding(end = 15.dp)
                                        .padding(top = 10.dp),
                                    textAlign = TextAlign.Center,
                                    color = Borgoña,
                                    fontSize = 28.sp
                                )
                            }
                        )
                    }){

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

                    SecondBodyContent(navController, viewModel, sharedViewModel)
                }
            }
        )
    }
}

@Composable
fun SecondBodyContent(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    val state = viewModel.state
    val nombreUsuario = sharedViewModel.getUserName()
    var idUsu by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        idUsu = viewModel.obtenerIdPorNombreUsu(nombreUsuario ?: "abc")
    }
    state.fkvas_usu = idUsu
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            Text(
                "usuario: $nombreUsuario"
            )
            Spacer(modifier = Modifier.height(25.dp))

            DefaultButton(
                onClick = {
                    viewModel.direccionPDF.value = false
                    viewModel.direccionHist.value = true
                    navController.navigate(route = Screens.MenuFicha.route)
                },
                text = "Modo historia"
            )
            DefaultButton(
                onClick = {
                    viewModel.direccionPDF.value = false
                    viewModel.direccionHist.value = false
                    navController.navigate(route = Screens.MenuFicha.route)
                },
                "Menú Ficha"
            )

            DefaultButton(
                onClick = {
                    viewModel.direccionPDF.value = true
                    viewModel.direccionHist.value = false
                    navController.navigate(route = Screens.MenuFicha.route)
                },
                "Generar PDF"
            )

            DefaultButton(
                onClick = {
                    viewModel.direccionPDF.value = true
                    viewModel.direccionHist.value = false
                    navController.navigate(route = Screens.VerArchivosPDF.route)
                },
                "Ver lista PDF"
            )


            //boton volver
            DefaultButton(
                onClick = { navController.navigate(route = Screens.MenuUsuario.route) },
                "volver"
            )
        }
    }
}

@Preview
@Composable
fun MenuPrincipalPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Menu Principal",
            style = TextStyle(color = Color.Green)
        )

        // Tutorial
        TextField(value = "state.name", onValueChange = {"viewModel.onNameChange(it)" })
        Button(onClick = {  }) {
            Text("guardar")
        }
        ElevatedButton(onClick = {  }) {
            Text("MenuCreacion")
        }

        // Otra cosa
        Button(onClick = {  }) {
            Text("volver")
        }
    }

}
