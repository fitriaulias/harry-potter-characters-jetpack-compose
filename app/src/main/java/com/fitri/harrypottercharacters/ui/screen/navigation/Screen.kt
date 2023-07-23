package com.fitri.harrypottercharacters.ui.screen.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object About: Screen("cart")
    object Detail: Screen("home/{id}") {
        fun createRoute(id: String) = "home/$id"
    }
}