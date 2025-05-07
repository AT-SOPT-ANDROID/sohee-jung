package org.sopt.at.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.component.navigationbar.BottomNavigationBar
import org.sopt.at.component.topbar.AtSoptMainTopBar
import org.sopt.at.navigation.MainRoute
import org.sopt.at.navigation.Route
import org.sopt.at.presentation.home.navigation.homeNavGraph
import org.sopt.at.presentation.live.navigation.liveNavGraph
import org.sopt.at.presentation.main.navigation.MainNavigator
import org.sopt.at.presentation.main.navigation.rememberMainNavigator
import org.sopt.at.presentation.my.navigation.My
import org.sopt.at.presentation.my.navigation.myNavGraph
import org.sopt.at.presentation.record.navigation.recordNavGraph
import org.sopt.at.presentation.search.navigation.searchNavGraph
import org.sopt.at.presentation.shorts.navigation.shortsNavGraph
import org.sopt.at.presentation.signin.navigation.SignIn
import org.sopt.at.presentation.signin.navigation.signInNavGraph
import org.sopt.at.presentation.signup.navigation.SignUp
import org.sopt.at.presentation.signup.navigation.signUpNavGraph
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    val currentDestination = navigator.currentDestination

    val showBars = when (currentDestination?.route)  {
        is SignIn, is SignUp, is My -> false
        else -> true
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(TvingTheme.colors.BasicBlack),
        topBar = {
            if (showBars) {
                AtSoptMainTopBar(
                    shareTvIconClick = {},
                    myIconClick = {
                        navigator.navigateToMy()
                    }
                )

            }
        },
        bottomBar = {
            if (showBars) {
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
            modifier = Modifier.background(TvingTheme.colors.BasicBlack)
        ) {
            signInNavGraph(
                paddingValues = innerPadding,
                onBackButtonClick = {},
                navigateToHome = navigator::navigateToHome,
                navigateToSignUpId = navigator::navigateToSignUpId,
            )
            signUpNavGraph(
                navigateToSignIn = navigator::navigateToSignIn
            )
            homeNavGraph(
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
                userId = "",
                onBackButtonClick = {},
                onLogoutButtonClick = navigator::navigateToSignIn,
                paddingValues = innerPadding
            )
        }
    }
}
