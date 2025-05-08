package org.sopt.at.presentation.home.tabbar

import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.persistentListOf

class TabViewModel : ViewModel() {

    val tabLists = persistentListOf(
        TabList(
            title = "드라마"
        ),
        TabList(
            title = "예능"
        ),
        TabList(
            title = "영화"
        ),
        TabList(
            title = "스포츠"
        ),
        TabList(
            title = "애니 "
        ),
        TabList(
            title = "뉴스"
        )
    )
}