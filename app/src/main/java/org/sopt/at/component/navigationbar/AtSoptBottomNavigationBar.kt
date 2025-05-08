package org.sopt.at.component.navigationbar

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.presentation.main.MainBottomTab
import org.sopt.at.ui.theme.BasicWhite
import org.sopt.at.ui.theme.Gray1
import org.sopt.at.ui.theme.TVINGTheme

@Composable
fun BottomNavigationBar(
    tabs: ImmutableList<MainBottomTab>,
    currentTab: MainBottomTab?,
    onTabSelected: (MainBottomTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        tabs.forEach { tab ->
            key(tab.route) {
                BottomBarItem(
                    tab = tab,
                    selected = tab == currentTab,
                    onClick = { onTabSelected(tab) }
                )
            }
        }
    }
}


@Composable
private fun RowScope.BottomBarItem(
    tab: MainBottomTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val itemSelectedIcon =
        remember(selected) { if (selected) tab.selectedIcon else tab.unselectedIcon }
    val itemSelectedTextColor =
        remember(selected) { if (selected) BasicWhite else Gray1 }

    Column(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxHeight()
            .align(Alignment.CenterVertically)
            .weight(1f)
            .selectable(
                selected = selected,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.Tab,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = itemSelectedIcon),
            contentDescription = tab.description,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = tab.label,
            color = itemSelectedTextColor
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun BottomNavigationBarPreview() {
    TVINGTheme {
        BottomNavigationBar(
            tabs = MainBottomTab.entries.toImmutableList(),
            currentTab = MainBottomTab.HOME,
            onTabSelected = {}
        )
    }
}