package org.sopt.at.presentation.shorts.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.shorts.ShortsScreen

@Serializable
data object ShortsRoute : MainRoute{
    override val route: String = "shorts"
}

fun NavController.navigateToShorts(navOptions: NavOptions) {
    navigate(ShortsRoute, navOptions)
}


fun NavGraphBuilder.shortsNavGraph() {
    composable<ShortsRoute> {
        ShortsScreen()
    }
}