package org.sopt.at.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.at.navigation.MainBottomTab
import org.sopt.at.presentation.home.navigation.Home
import org.sopt.at.presentation.home.navigation.navigateToHome
import org.sopt.at.presentation.live.navigation.navigateToLive
import org.sopt.at.presentation.my.navigation.navigateToMy
import org.sopt.at.presentation.record.navigation.navigateToRecord
import org.sopt.at.presentation.search.navigation.navigateToSearch
import org.sopt.at.presentation.shorts.navigation.navigateToShorts
import org.sopt.at.presentation.signin.navigation.SignIn
import org.sopt.at.presentation.signin.navigation.navigateToSignIn
import org.sopt.at.presentation.signup.navigation.navigateToSignUp

class MainNavigation(
    val navController: NavHostController
) {
    val startDestination = SignIn

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTab: MainBottomTab?
        @Composable get() = MainBottomTab.entries.find { tab ->
            currentDestination?.route == tab.route::class.qualifiedName
        }

    fun navigate(tab: MainBottomTab) {
        val navOptions = navOptions {
            popUpTo(tab.route) {
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainBottomTab.HOME -> navController.navigateToHome(navOptions)
            MainBottomTab.SHORTS -> navController.navigateToShorts(navOptions)
            MainBottomTab.LIVE -> navController.navigateToLive(navOptions)
            MainBottomTab.SEARCH -> navController.navigateToSearch(navOptions)
            MainBottomTab.RECORD -> navController.navigateToRecord(navOptions)
        }
    }

    fun navigateToSignIn() {
        navController.navigateToSignIn()
    }

    fun navigateToSignUpId() {
        navController.navigateToSignUp()
    }

    fun navigateToMy() {
        navController.navigateToMy()
    }

    fun navigateToHome() {
        navController.navigateToHome()
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigation = remember(navController) {
    MainNavigation(navController)
}