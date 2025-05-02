package org.sopt.at.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.home.HomeScreen

@Serializable
data object Home : MainRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(paddingValues: PaddingValues) {
    composable<Home> {
        HomeScreen(
            paddingValues = paddingValues
        )
    }
}