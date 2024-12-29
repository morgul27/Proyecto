package com.proyecto.navigation

sealed class Screens(val route: String) {
    object MenuUsuario: Screens("primer_screen")
    object BorrarUsuario: Screens("borrar_screen")
    object MenuPrincipal: Screens("segundo_screen")
    object MCrearUsuario: Screens("tercero_screen") //Menu Crear Usuario
    object MenuFicha: Screens("cuarto_screen")

    //creacion personaje
    object MCF: Screens("quinto_screen") //Menu Creacion Ficha
    object MCF2: Screens("sexto_screen") //Menu Creacion Ficha 2º parte final
    object MCFA: Screens("MCFA_screen") //Menu Creacion Ficha Atributo
    object MCFH: Screens("MCFH_screen") //Menu Creacion Ficha Habilidades
    object MCFD: Screens("MCFD_screen") //Menu Creacion Ficha Disciplinas
    object MejorarPersonaje: Screens("Septima_screen") //Menu para mejorar al vastago seleccionado


    //Modo Historia
    object InicioHist: Screens("Historia_screen") //prueba

    //Pantallas Nosferatu
    object P2Nosf: Screens("P2Nosf_screen")
    object P31Nosf: Screens("P31Nosf_screen")
    object P32Nosf: Screens("P32Nosf_screen")
    object P41Nosf: Screens("P41Nosf_screen")
    object P42Nosf: Screens("P42Nosf_screen")
    object P51Nosf: Screens("P51Nosf_screen")
    object P52Nosf: Screens("P52Nosf_screen")
    object P6Nosf: Screens("P6Nosf_screen")
    object P7Nosf: Screens("P7Nosf_screen")

    //Pantalla Brujah
    object P2Br: Screens("P2Br_screen")
    object P31Br: Screens("P31Br_screen")
    object P32Br: Screens("P32Br_screen")
    object P41Br: Screens("P41Br_screen")
    object P42Br: Screens("P42Br_screen")
    object P51Br: Screens("P51Br_screen")
    object P52Br: Screens("P52Br_screen")
    object P53Br: Screens("P53Br_screen")

    //Pantalla Gangrel
    object P2Gangr: Screens("P2Gangr_screen")
    object P31Gangr: Screens("P31Gangr_screen")
    object P32Gangr: Screens("P32Gangr_screen")
    object P33Gangr: Screens("P33Gangr_screen")
    object P34Gangr: Screens("P34Gangr_screen")
    object P4Gangr: Screens("P4Gangr_screen")
    object P51Gangr: Screens("P51Gangr_screen")
    object P52Gangr: Screens("P52Gangr_screen")
    object P61Gangr: Screens("P61Gangr_screen")
    object P62Gangr: Screens("P62Gangr_screen")
    object P7Gangr: Screens("P7Gangr_screen")
    object P8Gangr: Screens("P8Gangr_screen")
    object P81Gangr: Screens("P81Gangr_screen")
    object P82Gangr: Screens("P82Gangr_screen")



    object PantallaPDF: Screens("Decima_screen")
    object VerArchivosPDF: Screens("PDFREADER_screen")
    object PruebaCF: Screens("Prueba_screen")


}