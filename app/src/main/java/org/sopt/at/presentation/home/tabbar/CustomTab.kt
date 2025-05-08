package org.sopt.at.presentation.home.tabbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun CustomTab(
    tabs: ImmutableList<TabList>,
    dramaViewModel: DramaViewModel,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = TvingTheme.colors.BasicBlack,
            contentColor = TvingTheme.colors.Gray1,
            edgePadding = 0.dp,
            indicator = {},
            divider = {}
        ) {
            tabs.forEachIndexed { index, tabItem ->
                key(tabItem.title) {
                    Tab(
                        text = { Text(tabItem.title) },
                        selected = selectedTabIndex == index,
                        modifier = Modifier
                            .width(80.dp),
                        onClick = { selectedTabIndex = index }
                    )
                }
            }
        }

        when (selectedTabIndex) {
            0 -> DramaScreen(
                dramaViewModel = dramaViewModel
            )

            1 -> EntertainmentScreen()
            2 -> MovieScreen()
            3 -> SportsScreen()
            4 -> AnimationScreen()
            5 -> NewsScreen()
        }
    }
}