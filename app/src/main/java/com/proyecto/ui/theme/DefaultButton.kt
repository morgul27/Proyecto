package com.proyecto.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    fontFamily: FontFamily = ghoticFamily // reemplaza con tu `ghoticFamily` si lo tienes definido
) {
    Button(
        onClick = onClick,
        shape = shape,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Borgoña,
            contentColor = Blanco
        )
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontFamily = fontFamily
        )
    }
}