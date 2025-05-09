package org.sopt.at.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.component.navigationbar.BottomNavigationBar
import org.sopt.at.presentation.home.navigation.homeNavGraph
import org.sopt.at.presentation.live.navigation.liveNavGraph
import org.sopt.at.presentation.main.navigation.MainNavigator
import org.sopt.at.presentation.main.navigation.rememberMainNavigator
import org.sopt.at.presentation.my.navigation.myNavGraph
import org.sopt.at.presentation.record.navigation.recordNavGraph
import org.sopt.at.presentation.search.navigation.searchNavGraph
import org.sopt.at.presentation.shorts.navigation.shortsNavGraph
import org.sopt.at.presentation.signin.navigation.signInNavGraph
import org.sopt.at.presentation.signup.navigation.signUpNavGraph
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(TvingTheme.colors.BasicBlack),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            if (navigator.showBottomNavBar()) {
                BottomNavigationBar(
                    modifier = Modifier
                        .background(TvingTheme.colors.BasicBlack)
                        .navigationBarsPadding(),
                    tabs = MainBottomTab.entries.toImmutableList(),
                    currentTab = navigator.currentTab,
                    onTabSelected = { tab ->
                        navigator.navigate(tab)
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
            modifier = Modifier
                .background(TvingTheme.colors.BasicBlack)
        ) {
            val navController = navigator.navController

            signInNavGraph(
                paddingValues = innerPadding,
                onBackButtonClick = {},
                navigateToHome = navigator::navigateToHome,
                navigateToSignUpId = navigator::navigateToSignUpId,
                snackbarHostState = snackbarHostState
            )
            signUpNavGraph(
                navigateToSignIn = navigator::navigateToSignIn
            )
            homeNavGraph(
                navigateToMy = navigator::navigateToMy,
                paddingValues = innerPadding
            )
            shortsNavGraph(
                paddingValues = innerPadding
            )
            liveNavGraph(
                paddingValues = innerPadding
            )
            searchNavGraph(
                paddingValues = innerPadding
            )
            recordNavGraph(
                paddingValues = innerPadding
            )
            myNavGraph(
                onBackButtonClick = { navController.popBackStack() },
                onLogoutButtonClick = navigator::navigateToSignIn,
                paddingValues = innerPadding
            )
        }
    }
}
