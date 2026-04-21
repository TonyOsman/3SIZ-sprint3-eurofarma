/*
 *
 *  * Copyright (c) 2026 Inacia Santos
 *  *
 *  * Este código é propriedade de Inacia Santos.
 *  * Não pode ser copiado, modificado ou distribuído sem autorização.
 *
 */
package br.com.inaciasantos.eurofarma

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // Observa a rota atual
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            // Só mostra se NÃO for login
            if (currentRoute != "login") {
                BottomBar(navController)
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()) // 👈 só o bottom
        ) {

            composable("login") {
                LoginScreen(navController)
            }

            composable("model3d") {
                Model3DScreen {
                    navController.navigate("main")
                }
            }

            composable("main") {
                MainScreen(navController)
            }

            composable("ranking") {
                RankingScreen(navController)
            }

            composable("perfil") {
                PerfilScreen(navController)
            }

            composable(
                route = "curso/{titulo}",
                arguments = listOf(navArgument("titulo") { type = NavType.StringType })
            ) {
                val titulo = it.arguments?.getString("titulo") ?: ""
                CursoScreen(navController, titulo)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {

    val azulEurofarma = Color(0xFF0057B8)
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val navItems = listOf(
            Triple(Icons.Default.Home, "Home", "model3d"),
            Triple(Icons.Default.Search, "Busca", "main"),
            Triple(Icons.Default.Stars, "Adicionar", "ranking"),
            Triple(Icons.Default.AutoStories, "Reels", "main"),
            Triple(Icons.Default.Person, "Perfil", "perfil")
        )

        navItems.forEach { (icon, label, route) ->

            val isSelected = currentRoute == route

            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier
                    .size(if (route == "ranking") 32.dp else 26.dp)
                    .clickable {
                        navController.navigate(route) {
                            popUpTo("main")
                            launchSingleTop = true
                        }
                    },
                tint = if (isSelected) azulEurofarma else Color.Gray
            )
        }
    }
}