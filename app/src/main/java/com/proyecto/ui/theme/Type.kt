package com.proyecto.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.proyecto.R

val ghoticFamily = FontFamily(
    Font(R.font.vrev)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ghoticFamily,
        fontWeight = FontWeight.Normal,
        color = Blanco,
        fontSize = 24.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = ghoticFamily,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 24.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.5.sp
    ),

    bodySmall = TextStyle(
        fontFamily = ghoticFamily,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 20.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),




    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)