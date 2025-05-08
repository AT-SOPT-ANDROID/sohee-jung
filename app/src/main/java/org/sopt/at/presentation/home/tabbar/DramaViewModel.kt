package org.sopt.at.presentation.home.tabbar

import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.persistentListOf
import org.sopt.at.R
import org.sopt.at.presentation.home.HomeContents

class DramaViewModel: ViewModel() {

    val contents = persistentListOf(
        HomeContents(
            id = 1,
            image = R.drawable.content1
        ),
        HomeContents(
            id = 2,
            image = R.drawable.content2
        ),
        HomeContents(
            id = 3,
            image = R.drawable.content3
        ),
        HomeContents(
            id = 4,
            image = R.drawable.content4
        ),
        HomeContents(
            id = 5,
            image = R.drawable.content5
        ),
        HomeContents(
            id = 6,
            image = R.drawable.content6
        ),
        HomeContents(
            id = 7,
            image = R.drawable.content7
        )
    )
}