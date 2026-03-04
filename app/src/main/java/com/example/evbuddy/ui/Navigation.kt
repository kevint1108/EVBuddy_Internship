package com.example.evbuddy.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object MyRequests : Screen("my_requests")
    object Profile : Screen("profile")
}

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Home, "Home", Icons.Filled.Home),
    BottomNavItem(Screen.MyRequests, "My Requests", Icons.Filled.ListAlt),
    BottomNavItem(Screen.Profile, "Profile", Icons.Filled.Person)
)
