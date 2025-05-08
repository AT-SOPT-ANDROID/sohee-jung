package org.sopt.at.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.component.topbar.AtSoptMainTopBar
import org.sopt.at.presentation.home.tabbar.CustomTab
import org.sopt.at.presentation.home.tabbar.DramaViewModel
import org.sopt.at.presentation.home.tabbar.TabViewModel

@Composable
fun HomeScreen(
    navigateToMy: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    dramaViewModel: DramaViewModel = viewModel(),
    tabViewModel: TabViewModel = viewModel()
) {
    Column(
        modifier = modifier
    ) {
        AtSoptMainTopBar(
            shareTvIconClick = {},
            myIconClick = navigateToMy
        )

        CustomTab(
            tabViewModel.tabLists,
            dramaViewModel = dramaViewModel
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navigateToMy = {},
        paddingValues = PaddingValues()
    )
}