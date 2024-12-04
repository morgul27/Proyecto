package com.proyecto.screens.pdf

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.proyecto.MPViewModel
import com.proyecto.R
import com.proyecto.SharedViewModel
import com.proyecto.ui.theme.Borgoña
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.Typography
import com.proyecto.ui.theme.ghoticFamily
import java.io.File
import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.content.FileProvider

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VerArchivosPDF(navController: NavController, viewModel: MPViewModel, sharedViewModel: SharedViewModel){
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
                                IconButton(onClick = { navController.popBackStack() }) {
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


                    verLista()

                }
            }
        )
    }
}

@Composable
fun verLista() {
    val context = LocalContext.current
    val activity = context as? Activity // Convertir el contexto en actividad
    val pdfFiles = remember { mutableStateOf<List<File>>(emptyList()) } // Estado para almacenar los archivos PDF

    // Listar los archivos PDF (supongo que listPdfFilesInCustomDirectory lo implementas correctamente)
    LaunchedEffect(Unit) {
        // Listar los archivos PDF y actualizar el estado
        val files = listPdfFilesInCustomDirectory(context) // Esta función debe devolver una lista de archivos PDF
        pdfFiles.value = files
    }

    // Verificar y solicitar permisos
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicitar permisos si no han sido otorgados
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    100
                )
            } ?: Log.e("MainScreen", "No se pudo obtener la actividad")
        }
    }

    // Mostrar la lista de archivos PDF
    PdfListScreen(pdfFiles.value) { file ->
        // Manejo del clic en un archivo PDF
        Log.d("PDF", "Clic en archivo: ${file.name}")
    }
}

fun getCustomDirectory(context: Context): File? {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, "Fichas_Vastagos").apply { mkdirs() }
    }
    if (mediaDir != null) {
        if (!mediaDir.exists()) {
            mediaDir.mkdirs()
        }
    }
    return mediaDir
}

fun listPdfFilesInCustomDirectory(context: Context): List<File> {
    val customDir = getCustomDirectory(context)
    val customDira = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    if (customDir != null) {
        Log.d("CustomDirectory", "Ruta del directorio: ${customDir.absolutePath}")
    }

    val pdfFiles = customDir?.listFiles { file ->
        Log.d("CustomDirectory", "Archivo encontrado: ${file.name}")
        file.extension.equals("pdf", ignoreCase = true)
    }?.toList() ?: emptyList()

    if (customDir != null) {
        if (!customDir.exists()) {
            Log.e("Directory Check", "La ruta no existe.")
        } else {
            Log.e("Directory Check", "La ruta existe.")
        }
    }

    if (customDir != null) {
        if (!customDir.exists() || !customDir.isDirectory) {
            Log.e("Directory Check", "La ruta no es un directorio válido.")
        }
    }

    if (pdfFiles.isEmpty()) {
        Log.d("CustomDirectory", "No se encontraron archivos PDF")
    }

    return pdfFiles
}

@Composable
fun PdfListScreen(pdfFiles: List<File>, onPdfClick: (File) -> Unit) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(pdfFiles) { pdfFile ->
            PdfItem(file = pdfFile, onClick = { openPdfWithExternalApp(context, pdfFile) })
        }
    }
}

@Composable
fun PdfItem(file: File, onClick: () -> Unit) {
    val context = LocalContext.current
    val renderedBitmap = remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(file) {
        // Renderizar la primera página del archivo PDF
        renderedBitmap.value = renderPdfFirstPage(file)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            // Mostrar el nombre del archivo
            Text(text = file.name)

            // Mostrar el renderizado del PDF, si está disponible
            renderedBitmap.value?.let { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Vista previa del PDF",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}



//parte 2 ver PDFs

fun renderPdfFirstPage(file: File): Bitmap? {
    return try {
        val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        val pdfRenderer = PdfRenderer(fileDescriptor)
        val page = pdfRenderer.openPage(0)

        val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        page.close()
        pdfRenderer.close()
        bitmap
    } catch (e: Exception) {
        Log.e("PDFRender", "Error renderizando PDF: ${e.message}")
        null
    }
}


fun openPdfWithExternalApp(context: Context, file: File) {
    try {
        // Obtener URI del archivo utilizando FileProvider
        val pdfUri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider", // Define tu authority en el Manifest
            file
        )

        // Crear un Intent para abrir el archivo
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(pdfUri, "application/pdf")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION // Otorgar permiso de lectura
        }

        // Lanzar el Intent
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("OpenPDF", "Error al abrir el archivo PDF: ${e.message}")
        Toast.makeText(context, "No se pudo abrir el archivo PDF", Toast.LENGTH_SHORT).show()
    }
}

