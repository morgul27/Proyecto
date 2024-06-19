package com.proyecto.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.proyecto.MPViewModel
import com.proyecto.SharedViewModel
import com.proyecto.navigation.Screens
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import com.proyecto.ui.theme.ghoticFamily
import com.proyecto.R
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.proyecto.ui.theme.DefaultButton

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuUsuario(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
    ProyectoTheme {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme,
            typography = Typography,
            content = {
                Scaffold {

                }
                val state = viewModel.state
                state.name = ""

                val image = painterResource(R.drawable.marmol)
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


                BodyContent(navController, viewModel, sharedViewModel)
            }
        )
    }
}


@Composable
fun BodyContent(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel ){
    val state = viewModel.state
    val userName = sharedViewModel.userName


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            //insertar nombres de usuarios
            LaunchedEffect(Unit) {
                viewModel.obtenerUsuarios()
            }
            // Mostrar los nombres de los usuarios solo si el estado está cargado
            if (state.isLoading) {
                // Muestra un indicador de carga mientras se obtienen los usuarios
                CircularProgressIndicator()
            } else {
                if (state.names.isEmpty()) {
                    Text(text = "Parece que no tienes ningun usuario creado, por favor cree uno")
                } else {
                    Text("Lista de usuario:",
                        color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                    state.names.forEach { user ->
                        Card(
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                .widthIn(min = 50.dp)
                                .heightIn(min = 10.dp)
                                .clickable {
                                    // Establecer el nombre de usuario en el ViewModel compartido antes de navegar
                                    sharedViewModel.setUserName(user.name)
                                    // Navegar a la pantalla MenuPrincipal con el Bundle que contiene el nombre del usuario
                                    navController.navigate(route = Screens.MenuPrincipal.route)
                                }
                        ) {
                            Column (
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = user.name,
                                    color = Color.Black)
                            }
                        }
                    }
                }
            }
            DefaultButton(onClick = { navController.navigate(route = Screens.MCrearUsuario.route) },
                "Crear Usuario")

            DefaultButton(onClick = { navController.navigate(route = Screens.BorrarUsuario.route) },
                "Borrar usuarios"
            )
        }
    }
}


@Preview
@Composable
fun previu(){
    //prueba
    DefaultButton(
        onClick = {
            // acción del botón
        },
        text = "boton 1"
    )
}