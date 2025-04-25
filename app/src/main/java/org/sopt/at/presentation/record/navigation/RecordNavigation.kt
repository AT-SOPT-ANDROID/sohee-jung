package org.sopt.at.presentation.record.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.record.RecordScreen

@Serializable
data object RecordRoute : MainRoute{
    override val route: String = "record"
}

fun NavController.navigateToRecord(navOptions: NavOptions) {
    navigate(RecordRoute, navOptions)
}

//fun NavGraphBuilder.recordNavGraph() {
//    composable<RecordRoute> {
//        RecordScreen()
//    }
//}