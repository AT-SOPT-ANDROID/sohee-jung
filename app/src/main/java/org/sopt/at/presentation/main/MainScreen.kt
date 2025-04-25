package org.sopt.at.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.component.navigationbar.BottomNavigationBar
import org.sopt.at.component.topbar.AtSoptMainTopBar
import org.sopt.at.navigation.MainBottomTab
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.home.HomeScreen
import org.sopt.at.presentation.home.navigation.HomeRoute
import org.sopt.at.presentation.live.LiveScreen
import org.sopt.at.presentation.live.navigation.LiveRoute
import org.sopt.at.presentation.my.MyRoute
import org.sopt.at.presentation.my.MyScreen
import org.sopt.at.presentation.record.RecordScreen
import org.sopt.at.presentation.record.navigation.RecordRoute
import org.sopt.at.presentation.search.SearchScreen
import org.sopt.at.presentation.search.navigation.SearchRoute
import org.sopt.at.presentation.shorts.ShortsScreen
import org.sopt.at.presentation.shorts.navigation.ShortsRoute

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination

    val showBars = when (currentDestination?.route) {
        MyRoute::class.qualifiedName -> false
        else -> true
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            if (showBars) {
                AtSoptMainTopBar(
                    myIconClick = {
                        navController.navigate(MyRoute)
                    }
                )

            }
        },
        bottomBar = {
            if (showBars) {
                BottomNavigationBar(
                    modifier = Modifier
                        .background(Color.Black)
                        .navigationBarsPadding(),
                    tabs = MainBottomTab.entries,
                    currentTab = MainBottomTab.entries.find { tab ->
                        tab.route == navController.currentBackStackEntry?.destination
                    },
                    onTabSelected = {
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRoute
        ) {
            composable<HomeRoute> {
                HomeScreen(paddingValues = innerPadding)
            }
            composable<ShortsRoute> {
                ShortsScreen(paddingValues = innerPadding)
            }
            composable<LiveRoute> {
                LiveScreen(paddingValues = innerPadding)
            }
            composable<SearchRoute> {
                SearchScreen(paddingValues = innerPadding)
            }
            composable<RecordRoute> {
                RecordScreen(paddingValues = innerPadding)
            }
            composable<MyRoute> {
                MyScreen(
                    onBackButtonClick = {
                        navController.popBackStack()
                    },
                    userId = "dddddd",
                    paddingValues = innerPadding
                )
            }
        }
    }
}
