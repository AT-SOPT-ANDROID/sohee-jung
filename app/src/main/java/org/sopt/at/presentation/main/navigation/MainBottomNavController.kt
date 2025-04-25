package org.sopt.at.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import org.sopt.at.navigation.MainBottomTab
import org.sopt.at.presentation.home.navigation.navigateToHome
import org.sopt.at.presentation.live.navigation.navigateToLive
import org.sopt.at.presentation.record.navigation.navigateToRecord
import org.sopt.at.presentation.search.navigation.navigateToSearch
import org.sopt.at.presentation.shorts.navigation.navigateToShorts

class MainBottomNavController(
    val navController: NavController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = "home"

    val currentTab: MainBottomTab?
        @Composable get() = MainBottomTab.entries.find { tab ->
            currentDestination?.route == tab.route::class.qualifiedName
        }

    fun navigate(tab: MainBottomTab) {
        val navOptions = navOptions {
            popUpTo(MainBottomTab.Home) {
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainBottomTab.Home -> navController.navigateToHome(navOptions)
            MainBottomTab.Shorts -> navController.navigateToShorts(navOptions)
            MainBottomTab.Live -> navController.navigateToLive(navOptions)
            MainBottomTab.Search -> navController.navigateToSearch(navOptions)
            MainBottomTab.Record -> navController.navigateToRecord(navOptions)
        }
    }


}
