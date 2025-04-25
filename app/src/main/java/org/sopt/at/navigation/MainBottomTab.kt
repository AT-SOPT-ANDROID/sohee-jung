package org.sopt.at.navigation

import org.sopt.at.R
import org.sopt.at.presentation.home.navigation.HomeRoute
import org.sopt.at.presentation.live.navigation.LiveRoute
import org.sopt.at.presentation.record.navigation.RecordRoute
import org.sopt.at.presentation.search.navigation.SearchRoute
import org.sopt.at.presentation.shorts.navigation.ShortsRoute
import kotlin.math.round

enum class MainBottomTab(
    val label: String,
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val description: String = ""
) {
    Home(
        label = "홈",
        route = "home",
        selectedIcon = R.drawable.ic_nav_home_selected,
        unselectedIcon = R.drawable.ic_nav_home_unselected,
        description = "홈"
    ),
    Shorts(
        label = "쇼츠",
        route = "shorts",
        selectedIcon = R.drawable.ic_nav_shorts_selected,
        unselectedIcon = R.drawable.ic_nav_shorts_unselected,
        description = "쇼츠"
    ),
    Live(
        label = "라이브",
        route = "live",
        selectedIcon = R.drawable.ic_nav_live_selected,
        unselectedIcon = R.drawable.ic_nav_live_unselected,
        description = "라이브"
    ),
    Search(
        label = "검색",
        route = "search",
        selectedIcon = R.drawable.ic_nav_search_selected,
        unselectedIcon = R.drawable.ic_nav_search_unselected,
        description = "검색"


    ),
    Record(
        label = "기록",
        route = "record",
        selectedIcon = R.drawable.ic_nav_record_selected,
        unselectedIcon = R.drawable.ic_nav_record_unselected,
        description = "기록"
    )

}