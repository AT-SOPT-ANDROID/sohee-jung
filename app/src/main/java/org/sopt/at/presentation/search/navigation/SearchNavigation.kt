package org.sopt.at.presentation.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute

@Serializable
data object SearchRoute : MainRoute

fun NavController.navigateToSearch(navOptions: NavOptions) {
    navigate(SearchRoute, navOptions)
}

//fun NavGraphBuilder.searchNavGraph() {
//    composable<SearchRoute> {
//        SearchRoute
//    }
//}