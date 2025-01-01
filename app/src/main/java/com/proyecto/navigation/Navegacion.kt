package com.proyecto.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.proyecto.MPViewModel
import com.proyecto.SharedViewModel
import com.proyecto.screens.BorrarUsuario
import com.proyecto.screens.MCrearUsuario
import com.proyecto.screens.MenuFicha
import com.proyecto.screens.MenuPrincipal
import com.proyecto.screens.MenuUsuario
import com.proyecto.screens.MCF
import com.proyecto.screens.MCF2
import com.proyecto.screens.MCFA
import com.proyecto.screens.MCFD
import com.proyecto.screens.MCFH
import com.proyecto.screens.MCFU
import com.proyecto.screens.MejorarPersonaje
import com.proyecto.screens.PruebaCF
import com.proyecto.screens.mod_historia.InicioHist
import com.proyecto.screens.mod_historia.brujah.P2Br
import com.proyecto.screens.mod_historia.brujah.P31Br
import com.proyecto.screens.mod_historia.brujah.P32Br
import com.proyecto.screens.mod_historia.brujah.P41Br
import com.proyecto.screens.mod_historia.brujah.P42Br
import com.proyecto.screens.mod_historia.brujah.P51Br
import com.proyecto.screens.mod_historia.brujah.P52Br
import com.proyecto.screens.mod_historia.brujah.P53Br
import com.proyecto.screens.mod_historia.gangrel.P2Gangr
import com.proyecto.screens.mod_historia.gangrel.P31Gangr
import com.proyecto.screens.mod_historia.gangrel.P32Gangr
import com.proyecto.screens.mod_historia.gangrel.P33Gangr
import com.proyecto.screens.mod_historia.gangrel.P34Gangr
import com.proyecto.screens.mod_historia.gangrel.P4Gangr
import com.proyecto.screens.mod_historia.gangrel.P51Gangr
import com.proyecto.screens.mod_historia.gangrel.P52Gangr
import com.proyecto.screens.mod_historia.gangrel.P61Gangr
import com.proyecto.screens.mod_historia.gangrel.P62Gangr
import com.proyecto.screens.mod_historia.gangrel.P7Gangr
import com.proyecto.screens.mod_historia.gangrel.P81Gangr
import com.proyecto.screens.mod_historia.gangrel.P82Gangr
import com.proyecto.screens.mod_historia.gangrel.P8Gangr
import com.proyecto.screens.mod_historia.nosferatu.P2Nosf
import com.proyecto.screens.mod_historia.nosferatu.P31Nosf
import com.proyecto.screens.mod_historia.nosferatu.P32Nosf
import com.proyecto.screens.mod_historia.nosferatu.P41Nosf
import com.proyecto.screens.mod_historia.nosferatu.P42Nosf
import com.proyecto.screens.mod_historia.nosferatu.P51Nosf
import com.proyecto.screens.mod_historia.nosferatu.P52Nosf
import com.proyecto.screens.mod_historia.nosferatu.P6Nosf
import com.proyecto.screens.mod_historia.nosferatu.P7Nosf
import com.proyecto.screens.pdf.PantallaPDF
import com.proyecto.screens.pdf.VerArchivosPDF

@Composable
fun Navegacion(viewModel: MPViewModel){
    val sharedViewModel = remember { SharedViewModel() }

    // Iniciar el NavHostController
    val navController = rememberNavController()

    // Definir el contenido del NavHost
    NavHost(navController = navController, startDestination = Screens.MenuUsuario.route) {
        // Menu usuarios
        composable(route = Screens.MenuUsuario.route) {
            MenuUsuario(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.MCrearUsuario.route) {
            MCrearUsuario(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.BorrarUsuario.route) {
            BorrarUsuario(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        //menus principal
        composable(route = Screens.MenuPrincipal.route) {
            MenuPrincipal(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.MenuFicha.route) {
            MenuFicha(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.PantallaPDF.route) {
            PantallaPDF(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.VerArchivosPDF.route) {
            VerArchivosPDF(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }

        //Creacion de personajes
        composable(route = Screens.MCF.route) {
            MCF(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.MCF2.route) {
            MCF2(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.MCFA.route) {
            MCFA(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.MCFH.route) {
            MCFH(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.MCFD.route) {
            MCFD(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.MCFU.route) {
            MCFU(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }

        composable(route = Screens.MejorarPersonaje.route) {
            MejorarPersonaje(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }

        //historia
        composable(route = Screens.InicioHist.route) {
            InicioHist(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }



        //menus Nosferatu
        composable(route = Screens.P2Nosf.route) {
            P2Nosf(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P31Nosf.route) {
            P31Nosf(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P32Nosf.route) {
            P32Nosf(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P41Nosf.route) {
            P41Nosf(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P42Nosf.route) {
            P42Nosf(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P51Nosf.route) {
            P51Nosf(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P52Nosf.route) {
            P52Nosf(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P6Nosf.route) {
            P6Nosf(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P7Nosf.route) {
            P7Nosf(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }

        //menus Brujah
        composable(route = Screens.P2Br.route) {
            P2Br(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P31Br.route) {
            P31Br(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P32Br.route) {
            P32Br(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P41Br.route) {
            P41Br(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P42Br.route) {
            P42Br(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P51Br.route) {
            P51Br(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P52Br.route) {
            P52Br(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P53Br.route) {
            P53Br(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }

        //menus Gangrel
        composable(route = Screens.P2Gangr.route) {
            P2Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P31Gangr.route) {
            P31Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P32Gangr.route) {
            P32Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P33Gangr.route) {
            P33Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P34Gangr.route) {
            P34Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P4Gangr.route) {
            P4Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P51Gangr.route) {
            P51Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P52Gangr.route) {
            P52Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P61Gangr.route) {
            P61Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P62Gangr.route) {
            P62Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P7Gangr.route) {
            P7Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P8Gangr.route) {
            P8Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P81Gangr.route) {
            P81Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.P82Gangr.route) {
            P82Gangr(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }

        composable(route = Screens.PruebaCF.route) {
            PruebaCF(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
    }

    // Manejar el botón de retroceso
    BackHandler {
        if (navController.currentBackStackEntry?.destination?.route == Screens.MenuUsuario.route) {

        }
        else if(navController.currentBackStackEntry?.destination?.route == Screens.MenuPrincipal.route) {
            // Si estamos en MenuPrincipal, navegar a MenuUsuario en lugar de hacer retroceder
            navController.navigate(Screens.MenuUsuario.route) {
                // Limpiar la pila de navegación para que no pueda retroceder a MenuPrincipal
                popUpTo(Screens.MenuPrincipal.route) { inclusive = true }
            }
        }
        else {
            navController.popBackStack()
        }
    }
}