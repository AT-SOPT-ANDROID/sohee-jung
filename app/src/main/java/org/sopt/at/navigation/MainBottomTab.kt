package org.sopt.at.navigation

import org.sopt.at.R
import org.sopt.at.presentation.home.navigation.HomeRoute
import org.sopt.at.presentation.live.navigation.LiveRoute
import org.sopt.at.presentation.record.navigation.RecordRoute
import org.sopt.at.presentation.search.navigation.SearchRoute
import org.sopt.at.presentation.shorts.navigation.ShortsRoute

enum class MainBottomTab(
    val label: String,
    val route: MainRoute,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val description: String = ""
) {
    Home(
        label = "홈",
        route = HomeRoute,
        selectedIcon = R.drawable.ic_nav_home_selected,
        unselectedIcon = R.drawable.ic_nav_home_unselected,
        description = "홈"
    ),
    Shorts(
        label = "쇼츠",
        route = ShortsRoute,
        selectedIcon = R.drawable.ic_nav_shorts_selected,
        unselectedIcon = R.drawable.ic_nav_shorts_unselected,
        description = "쇼츠"
    ),
    Live(
        label = "라이브",
        route = LiveRoute,
        selectedIcon = R.drawable.ic_nav_live_selected,
        unselectedIcon = R.drawable.ic_nav_live_unselected,
        description = "라이브"
    ),
    Search(
        label = "검색",
        route = SearchRoute,
        selectedIcon = R.drawable.ic_nav_search_selected,
        unselectedIcon = R.drawable.ic_nav_search_unselected,
        description = "검색"


    ),
    Record(
        label = "기록",
        route = RecordRoute,
        selectedIcon = R.drawable.ic_nav_record_selected,
        unselectedIcon = R.drawable.ic_nav_record_unselected,
        description = "기록"
    )

}