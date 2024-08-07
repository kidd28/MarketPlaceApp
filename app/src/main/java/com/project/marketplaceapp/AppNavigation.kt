package com.project.marketplaceapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.marketplaceapp.pages.HomePage
import com.project.marketplaceapp.pages.LoginPage
import com.project.marketplaceapp.pages.SignupPage

@Composable
fun AppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
            LoginPage(modifier,navController, authViewModel)
        }
        composable("signup") {
            SignupPage(modifier,navController, authViewModel)
        }
        composable("homepage") {
            HomePage(modifier,navController, authViewModel)
        }
    }
    )
}