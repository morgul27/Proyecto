package com.proyecto.screens.pdf

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.proyecto.ui.theme.ProyectoTheme
import java.io.File
import com.proyecto.R
import com.proyecto.SharedViewModel
import com.proyecto.navigation.Screens
import com.proyecto.ui.theme.Blanco
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.DefaultButton
import com.proyecto.ui.theme.ghoticFamily

private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34

private fun foregroundPermissionApproved(context: Context): Boolean {
    val writePermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val readPermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.READ_EXTERNAL_STORAGE
    )

    return writePermissionFlag && readPermissionFlag
}

private fun requestForegroundPermission(context: Context) {
    val provideRationale = foregroundPermissionApproved(context = context)
    if (provideRationale) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    } else {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPDF(navController: NavController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    ProyectoTheme {
        requestForegroundPermission(context)
        Surface() {
            Scaffold (
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,
                            titleContentColor = Borgoña,
                            navigationIconContentColor = Borgoña
                        ),
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Arrow back"
                                )
                            }
                        },
                        title = {
                            Text(
                                text = "Archivo PDF",
                                fontFamily = ghoticFamily,
                                maxLines = 1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentSize(Alignment.Center)
                                    .padding(end = 15.dp)
                                    .padding(top = 10.dp),
                                textAlign = TextAlign.Center,
                                color = Borgoña,
                                fontSize = 30.sp
                            )
                        }
                    )
                }){

                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Image(
                        painter = painterResource(R.drawable.marmol),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.alpha(0.6F)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            GenerarPDF(context, getDirectory(context), sharedViewModel)
                            navController.navigate(route = Screens.MenuPrincipal.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(60.dp)
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Borgoña,
                            contentColor = Blanco
                        ),
                        shape = RoundedCornerShape(8,8,8,8)
                    ) {
                        Text(
                            text = "Crear PDF",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontFamily = ghoticFamily
                        )
                    }
                    DefaultButton(onClick = { navController.navigate(route = Screens.MenuFicha.route) },
                        "volver"
                    )
                }
            }
        }
    }

}

fun getDirectory(context: Context): File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, "Fichas_Vastagos").apply { mkdirs() }
    }

    //cambiar esto cuando se solucione
    val mediaDira = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir

}



