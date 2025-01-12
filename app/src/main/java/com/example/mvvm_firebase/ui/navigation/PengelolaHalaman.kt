package com.example.mvvm_firebase.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_firebase.ui.pages.HomeScreen
import com.example.mvvm_firebase.ui.pages.InsertMhsView

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController ()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToltemEntry = {
                    navController.navigate(DestinasiInsert.route)
                },
            )
        }

        composable(DestinasiInsert.route){
            InsertMhsView(
                onBack = {navController.popBackStack()},
                onNavigate = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }

    }
}