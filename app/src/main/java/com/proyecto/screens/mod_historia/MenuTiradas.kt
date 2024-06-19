package com.proyecto.screens.mod_historia

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.proyecto.MPViewModel
import com.proyecto.R
import com.proyecto.ui.theme.Blanco
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.DefaultButton
import com.proyecto.ui.theme.ghoticFamily
import kotlinx.coroutines.delay

@Composable
fun MenuTirardas(
    viewModel: MPViewModel,
    nUsuario: Int,
    nNecesario: Int,
    navigateTo: () -> Unit,
    navigateTo2: (() -> Unit)? = null,
    textoBueno: String?,
    textoMalo: String?
) {
    var diceResults by remember { mutableStateOf(List(nUsuario) { 1 }) }
    var aciertos by remember { mutableStateOf(0) }
    var mensaje by remember { mutableStateOf(false) }
    var changingImages by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 55.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        item {

            if (nUsuario == 0) {
                Text(text = "No tienes dados")
                Text(text = "Has perdido")
                changingImages = false
            } else {
                LaunchedEffect(Unit) {
                    delay(2000L) // Espera 2 segundos
                    changingImages = false // Detiene el cambio de imágenes
                }
                LaunchedEffect(changingImages) {
                    while (changingImages) {
                        diceResults = diceResults.map { (1..10).random() }
                        delay(10L) // Cambia la imagen cada 10 milisegundos
                    }
                }
                // Contar los aciertos cuando el bucle de imágenes pare
                aciertos = diceResults.count { it > 5 }

                // Actualizar la UI cuando los dados se detengan y los aciertos estén disponibles
                LaunchedEffect(changingImages) {
                    if (!changingImages) {
                        if (aciertos >= nNecesario) {
                            mensaje = true
                        }
                    }
                }

                if (!changingImages) {
                    if (mensaje == true) {
                        if (textoBueno != null) {
                            Text(
                                text = "$textoBueno",
                                color = Color.White
                            )
                        }
                    } else {
                        if (textoMalo != null) {
                            Text(
                                text = "$textoMalo",
                                color = Color.White
                            )
                        }
                    }
                } else {
                    Text(text = "  ")
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (result in diceResults) {
                    val imageResource = when (result) {
                        1 -> R.drawable.d1
                        2 -> R.drawable.d2
                        3 -> R.drawable.d3
                        4 -> R.drawable.d4
                        5 -> R.drawable.d5
                        6 -> R.drawable.d6
                        7 -> R.drawable.d7
                        8 -> R.drawable.d8
                        9 -> R.drawable.d9
                        else -> R.drawable.d10

                    }
                    Image(
                        painter = painterResource(imageResource),
                        contentDescription = result.toString(),
                        modifier = Modifier
                            .weight(1f)
                            .size(calculateImageSize(nUsuario))
                            .align(Alignment.CenterVertically)
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!changingImages) {
                if (mensaje == true) {
                    Button(onClick = {
                        viewModel.perder = false
                        viewModel.cambioMenuHist()
                        navigateTo()
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Borgoña,
                            contentColor = Blanco
                        ),
                        shape = RoundedCornerShape(8,8,8,8)

                    ) {
                        Text("Continuar",
                            color = Color.White,
                            fontFamily = ghoticFamily)
                    }
                } else {
                    Button(onClick = {
                        viewModel.cambioMenuHist()
                        if (navigateTo2 != null) {
                            navigateTo2()
                        }
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Borgoña,
                            contentColor = Blanco
                        ),
                        shape = RoundedCornerShape(8,8,8,8)
                    ) {

                        if(viewModel.perder == true){
                            Text("Volver al menu principal",
                                color = Color.White,
                                fontFamily = ghoticFamily)
                        }else{
                            Text("Continuar",
                                color = Color.White,
                                fontFamily = ghoticFamily)
                        }
                    }
                }
            } else {
                DefaultButton(onClick = { },
                    text = "Esperar"
                )
            }

            //borrar este boton antes de tenerlo terminado
            DefaultButton(onClick = { viewModel.cambioMenuHist() },
                text = "volver"
            )
        }
    }
}


//funcion para calcular tamaño
private fun calculateImageSize(numImages: Int): Dp {
    // Cambia el tamaño máximo según tus necesidades
    val maxWidth = 500.dp
    val imageSize = (maxWidth / numImages.coerceAtLeast(1)).coerceAtMost(maxWidth)
    return imageSize
}