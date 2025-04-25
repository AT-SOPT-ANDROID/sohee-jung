package org.sopt.at.presentation.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.at.presentation.home.navigation.HomeRoute
import org.sopt.at.presentation.home.navigation.homeNavGraph
import org.sopt.at.presentation.live.navigation.liveNavGraph
import org.sopt.at.presentation.record.navigation.recordNavGraph
import org.sopt.at.presentation.search.navigation.searchNavGraph
import org.sopt.at.presentation.shorts.navigation.shortsNavGraph


//@Composable
//fun MainBottomNavHost(
//    modifier: Modifier = Modifier,
//    navigator: MainBottomNavController,
//    paddingValues: PaddingValues
//    ){
//    val navController = navigator.navController
//
//    Navhost(
//        navController = navController,
//        startDestination = HomeRoute.route,
//        modifier = modifier
//            .padding(paddingValues)
//    ){
//        homeNavGraph()
//        shortsNavGraph()
//        liveNavGraph()
//        searchNavGraph()
//        recordNavGraph()
//
//    }
//
//}