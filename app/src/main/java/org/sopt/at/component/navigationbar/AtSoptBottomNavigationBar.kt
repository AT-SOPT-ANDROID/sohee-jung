package org.sopt.at.component.navigationbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.navigation.MainBottomTab
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    tabs: List<MainBottomTab>,
    currentTab: MainBottomTab?,
    onTabSelected: (MainBottomTab) -> Unit
) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            tabs.forEach { tab ->
                BottomBarItem(
                    tab = tab,
                    selected = tab == currentTab,
                    onClick = { onTabSelected(tab) }
                )
            }
        }
    }


@Composable
private fun RowScope.BottomBarItem(
    modifier: Modifier = Modifier,
    tab: MainBottomTab,
    selected: Boolean,
    onClick: () -> Unit
) {
    val itemSelectedIcon = if (selected) tab.selectedIcon else tab.unselectedIcon
    val itemSelectedTextColor = if (selected) Color.White else Color.LightGray
    Column(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxHeight()
            .align(Alignment.CenterVertically)
            .weight(1f)
            .selectable(
                selected = selected,
                role = Role.Tab,
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically)
    ) {
        Icon(
            painter = painterResource(id = itemSelectedIcon),
            contentDescription = tab.description,
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
    ATSOPTANDROIDTheme {
        BottomNavigationBar(
            tabs = MainBottomTab.values().toList(),
            currentTab = MainBottomTab.Home,
            onTabSelected = {}
        )
    }
}