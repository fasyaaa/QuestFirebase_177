package com.example.pam14.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pam14.ui.home.pages.HomeScreen
import com.example.pam14.ui.insert.page.InsertMhsView

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToltemEntry = {
                    navController.navigate(DestinasiInsert.route)
                },
                onDetailClick = {
                    navController.navigate(DestinasiDetail.route)
                }
            )
        }
        composable(DestinasiInsert.route){
            InsertMhsView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }
        composable(route = "${DestinasiDetail.route}/{nim}",
            arguments = listOf(
                navArgument("nim"){
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim")
//            nim?.let {
//                DetailView(
//                    nim = it,
//                    navigateBack = {navController.popBackStack()}
//                )
//            }
        }
    }
}