package org.sopt.at.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.component.navigationbar.BottomNavigationBar
import org.sopt.at.component.topbar.AtSoptMainTopBar
import org.sopt.at.navigation.MainBottomTab
import org.sopt.at.presentation.home.HomeScreen
import org.sopt.at.presentation.home.navigation.HomeRoute
import org.sopt.at.presentation.live.LiveScreen
import org.sopt.at.presentation.live.navigation.LiveRoute
import org.sopt.at.presentation.main.navigation.MainBottomNavController
import org.sopt.at.presentation.record.RecordScreen
import org.sopt.at.presentation.record.navigation.RecordRoute
import org.sopt.at.presentation.search.SearchScreen
import org.sopt.at.presentation.search.navigation.SearchRoute
import org.sopt.at.presentation.shorts.ShortsScreen
import org.sopt.at.presentation.shorts.navigation.ShortsRoute
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AtSoptMainTopBar(
                myIconClick = {}
            )

        },
        bottomBar = {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            val currentTab = MainBottomTab.entries.find { it.route == currentRoute }

            BottomNavigationBar(
                modifier = Modifier
                    .background(Color.Black)
                    .navigationBarsPadding(),
                tabs = MainBottomTab.entries,
                currentTab = currentTab,
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
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home"
        ){
            composable("home") {
                HomeScreen(paddingValues = innerPadding)
            }
            composable("shorts") {
                ShortsScreen(paddingValues = innerPadding)
            }
            composable("live") {
                LiveScreen(paddingValues = innerPadding)
            }
            composable("search") {
                SearchScreen(paddingValues = innerPadding)
            }
            composable("record") {
                RecordScreen(paddingValues = innerPadding)
            }
        }
    }
}
