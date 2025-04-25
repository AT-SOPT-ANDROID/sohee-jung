package org.sopt.at.presentation.record.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute

@Serializable
data object RecordRoute : MainRoute

fun NavController.navigateToRecord(navOptions: NavOptions) {
    navigate(RecordRoute, navOptions)
}

//fun NavGraphBuilder.recordNavGraph() {
//    composable<RecordRoute> {
//        RecordScreen()
//    }
//}