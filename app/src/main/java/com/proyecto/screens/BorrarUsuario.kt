package com.proyecto.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import com.proyecto.ui.theme.DefaultButton

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BorrarUsuario(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
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


                BodyBorrarUsuario(navController, viewModel, sharedViewModel)
            }
        )
    }
}


@Composable
fun BodyBorrarUsuario(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel ){
    val state = viewModel.state
    val userName = sharedViewModel.userName


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
                    state.names.forEach { usu ->
                        Card(
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                                .clickable {
                                    sharedViewModel.userId = usu.id
                                    Log.d("MIRA AQUI", "userid: ${usu.id}")
                                    Log.d("MIRA AQUI", "nombre usuario: ${usu.name}")

                                    usu.id?.let { viewModel.eliminarUsuarioPorId(it) }
                                    // Navegar a la pantalla MenuPrincipal con el Bundle que contiene el nombre del usuario
                                    navController.navigate(route = Screens.BorrarUsuario.route)
                                }
                        ) {
                            Text(text = usu.name,
                                color = Color.Black)
                        }
                    }
                }
            }
            DefaultButton(onClick = { navController.navigate(route = Screens.MenuUsuario.route) },
                "volver"
            )
        }
    }
}
