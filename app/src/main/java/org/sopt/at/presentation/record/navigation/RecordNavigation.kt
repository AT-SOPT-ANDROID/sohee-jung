package org.sopt.at.presentation.record.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.record.RecordScreen

@Serializable
data object Record : MainRoute

fun NavController.navigateToRecord(navOptions: NavOptions) {
    navigate(Record, navOptions)
}

fun NavGraphBuilder.recordNavGraph(paddingValues: PaddingValues) {
    composable<Record> {
        RecordScreen(paddingValues = paddingValues)
    }
}