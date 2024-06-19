package com.proyecto.screens.pdf

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.proyecto.R
import com.proyecto.SharedViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun GenerarPDF(context: Context, directory: File, sharedViewModel: SharedViewModel){
    var cont = 0
    var distancia = 0
    val pageHeight = 1120
    val pageWidth = 792
    val pdfDocument = PdfDocument()
    val paint = Paint()
    val title = Paint()
    val myPageInfo = PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val myPage = pdfDocument.startPage(myPageInfo)
    val canvas: Canvas = myPage.canvas
    val bitmap: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.fichavas))
    val scaleBitmap: Bitmap? = Bitmap.createScaledBitmap(bitmap!!, 792, 1120, false)
    canvas.drawBitmap(scaleBitmap!!, 0f, 0f, paint)

    //Puntos de Fuerza
    while (cont < sharedViewModel.vasFuerza.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (210f + distancia), 295f, paint)
        distancia += 10
    }
    while (cont < 6){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (210f + distancia), 295f, paint)
        distancia += 10
    }

    //Puntos de Destr
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasDestr.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (210f + distancia), 320f, paint)
        distancia += 10
    }
    while (cont < 6){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (210f + distancia), 320f, paint)
        distancia += 10
    }

    //Puntos de Resist
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasResist.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (210f + distancia), 350f, paint)
        distancia += 10
    }
    while (cont < 6){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (210f + distancia), 350f, paint)
        distancia += 10
    }

    //Puntos de Carisma
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasCarisma.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (420f + distancia), 295f, paint)
        distancia += 10
    }
    while (cont < 6){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (420f + distancia), 295f, paint)
        distancia += 10
    }

    //Puntos de Manipulacion
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasMan.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (420f + distancia), 320f, paint)
        distancia += 10
    }
    while (cont < 6){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (420f + distancia), 320f, paint)
        distancia += 10
    }

    //Puntos de Compostura
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasCompostura.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (420f + distancia), 350f, paint)
        distancia += 10
    }
    while (cont < 6){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (420f + distancia), 350f, paint)
        distancia += 10
    }

    //Puntos de Inteligencia
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasIntel.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (630f + distancia), 295f, paint)
        distancia += 10
    }
    while (cont < 6){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (630f + distancia), 295f, paint)
        distancia += 10
    }

    //Puntos de Astucia
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasAstucia.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (630f + distancia), 320f, paint)
        distancia += 10
    }
    while (cont < 6){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (630f + distancia), 320f, paint)
        distancia += 10
    }

    //Puntos de Resolución
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasResolucion.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (630f + distancia), 350f, paint)
        distancia += 10
    }
    while (cont < 6){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (630f + distancia), 350f, paint)
        distancia += 10
    }

    //Puntos de Salud
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasSalud.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (255f + distancia), 390f, paint)
        distancia += 11
    }
    while (cont < 10){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (255f + distancia), 390f, paint)
        distancia += 11
    }

    //Puntos de Fuerza de Voluntad
    cont = 0
    distancia = 0
    while (cont < sharedViewModel.vasVoluntad.value!!){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (420f + distancia), 390f, paint)
        distancia += 11
    }
    while (cont < 10){
        cont++
        val bitmap1: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.puntos_blanco))
        val scaleBitmap1: Bitmap? = Bitmap.createScaledBitmap(bitmap1!!, 10, 10, false)
        canvas.drawBitmap(scaleBitmap1!!, (420f + distancia), 390f, paint)
        distancia += 11
    }





    title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    title.textSize = 15f
    title.color = ContextCompat.getColor(context, R.color.purple_200)
    //canvas.drawText("Jetpack Compose", 400f, 100f, title)
    //canvas.drawText("Make it Easy", 400f, 80f, title)


    title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
    title.color = ContextCompat.getColor(context, R.color.black)
    title.textSize = 14f
    title.textAlign = Paint.Align.CENTER
    //Nombre
    canvas.drawText("${sharedViewModel.vasName.value}", 165f, 160f, title)
    //Clan
    canvas.drawText(" ${sharedViewModel.vasClan.value}", 165f, 195f, title)
    //Generación
    canvas.drawText("${sharedViewModel.vasGeneracion.value}", 160f, 225f, title)
    pdfDocument.finishPage(myPage)
    val file = File(directory, "PDF_De_Vastago.pdf")

    try {
        pdfDocument.writeTo(FileOutputStream(file))
        Toast.makeText(context, "Generado PDF satisfactoriamente", Toast.LENGTH_SHORT).show()
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
    pdfDocument.close()
}

fun drawableToBitmap(drawable: Drawable): Bitmap? {
    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

