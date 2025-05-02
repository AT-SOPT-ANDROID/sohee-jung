package org.sopt.at.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import org.sopt.at.R
import org.sopt.at.presentation.home.navigation.Home
import org.sopt.at.presentation.live.navigation.Live
import org.sopt.at.presentation.record.navigation.Record
import org.sopt.at.presentation.search.navigation.Search
import org.sopt.at.presentation.shorts.navigation.Shorts

@Immutable
enum class MainBottomTab(
    val label: String,
    val route: MainRoute,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val description: String = ""
) {
    HOME(
        label = "홈",
        route = Home,
        selectedIcon = R.drawable.ic_nav_home_selected,
        unselectedIcon = R.drawable.ic_nav_home_unselected,
        description = "홈"
    ),
    SHORTS(
        label = "쇼츠",
        route = Shorts,
        selectedIcon = R.drawable.ic_nav_shorts_selected,
        unselectedIcon = R.drawable.ic_nav_shorts_unselected,
        description = "쇼츠"
    ),
    LIVE(
        label = "라이브",
        route = Live,
        selectedIcon = R.drawable.ic_nav_live_selected,
        unselectedIcon = R.drawable.ic_nav_live_unselected,
        description = "라이브"
    ),
    SEARCH(
        label = "검색",
        route = Search,
        selectedIcon = R.drawable.ic_nav_search_selected,
        unselectedIcon = R.drawable.ic_nav_search_unselected,
        description = "검색"


    ),
    RECORD(
        label = "기록",
        route = Record,
        selectedIcon = R.drawable.ic_nav_record_selected,
        unselectedIcon = R.drawable.ic_nav_record_unselected,
        description = "기록"
    )

}