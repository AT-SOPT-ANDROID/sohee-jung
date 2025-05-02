package org.sopt.at.presentation.shorts.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.shorts.ShortsScreen

@Serializable
data object Shorts : MainRoute

fun NavController.navigateToShorts(navOptions: NavOptions) {
    navigate(Shorts, navOptions)
}


fun NavGraphBuilder.shortsNavGraph(paddingValues: PaddingValues) {
    composable<Shorts> {
        ShortsScreen(paddingValues = paddingValues)
    }
}