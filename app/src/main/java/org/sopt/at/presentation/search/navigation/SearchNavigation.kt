package org.sopt.at.presentation.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.search.SearchScreen

@Serializable
data object Search : MainRoute

fun NavController.navigateToSearch(navOptions: NavOptions) {
    navigate(Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(paddingValues: PaddingValues) {
    composable<Search> {
        SearchScreen(paddingValues = paddingValues)
    }
}