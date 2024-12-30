package com.proyecto.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyecto.R
import com.proyecto.ui.theme.Blanco
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.DefaultButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun explicacion(
    texto: String,
    textoT: String,
    textoExpl: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    textColor: Color = Blanco, // Color del texto
    lineHeight: TextUnit = 20.sp
) {
    var showDialog by remember { mutableStateOf(false) }

    // Fila con fondo personalizado
    Row(
        modifier = modifier.clickable { showDialog = true } // Hace clickeable toda la fila
    ) {
        // Texto con color personalizado
        Text(
            text = texto,
            fontSize = fontSize,
            lineHeight = lineHeight,
            color = textColor // Cambia el color del texto
        )

        // Imagen justo al lado del texto
        Image(
            painter = painterResource(id = R.drawable.infoblanco), // Cambia por tu recurso
            contentDescription = "Icono de información",
            modifier = Modifier.size(14.dp) // Tamaño de la imagen
        )
    }

    // Diálogo
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            // Contenido completo del diálogo (envolvemos con Surface para el fondo)
            content = {
                Surface(
                    color = Borgoña, // Fondo del diálogo
                    modifier = Modifier.padding(5.dp),
                    shape = RoundedCornerShape(10)
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(25.dp))

                        // Título del diálogo
                        Text(
                            text = textoT,
                            color = Blanco, // Color del texto del título
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(start = 15.dp,bottom = 8.dp)
                        )
                        // Contenido del diálogo
                        Text(
                            //text = textoExpl,
                            text = "Las razas es un factor determinante para las personas en el rol, teniendo sus propias repercusiones en el mundo o afectando su desenvolvimiento en las ciudades que existen. Adicionalmente cada raza posee habilidades útiles para el día a día. ",
                            color = Blanco, // Color del texto del contenido
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(15.dp)
                        )
                        // Botones
                        Row(
                            modifier = Modifier
                                .padding(end = 15.dp, top = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
                        ) {
                            DefaultButton(onClick = { showDialog = false },
                                containerColor = Color.Black,
                                text = "Aceptar"
                            )
                            DefaultButton(onClick = { showDialog = false },
                                containerColor = Color.Black,
                                text = "Cerrar"
                            )
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
            }
        )
    }
}

