package org.sopt.at.presentation.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute

@Serializable
data object SearchRoute : MainRoute{
    override val route: String = "search"
}

fun NavController.navigateToSearch(navOptions: NavOptions) {
    navigate(SearchRoute, navOptions)
}

//fun NavGraphBuilder.searchNavGraph() {
//    composable<SearchRoute> {
//        SearchRoute
//    }
//}