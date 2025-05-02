package org.sopt.at.presentation.my.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.my.MyScreen

@Serializable
data object My : MainRoute

fun NavController.navigateToMy(navOptions: NavOptions? = null) {
    navigate(My, navOptions)
}

fun NavGraphBuilder.myNavGraph(
    userId: String,
    onLogoutButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    paddingValues: PaddingValues,
) {
    composable<My> {
        MyScreen(
            userId = userId,
            onBackButtonClick = onBackButtonClick,
            onLogoutButtonClick = onLogoutButtonClick,
            paddingValues = paddingValues,
        )
    }
}