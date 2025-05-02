package org.sopt.at.presentation.live.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.live.LiveScreen

@Serializable
data object Live : MainRoute

fun NavController.navigateToLive(navOptions: NavOptions) {
    navigate(Live, navOptions)
}

fun NavGraphBuilder.liveNavGraph(paddingValues: PaddingValues) {
    composable<Live> {
        LiveScreen(paddingValues = paddingValues)
    }
}