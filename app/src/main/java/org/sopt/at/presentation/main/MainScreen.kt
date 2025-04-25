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
import androidx.navigation.compose.rememberNavController
import org.sopt.at.component.navigationbar.BottomNavigationBar
import org.sopt.at.navigation.MainBottomTab
import org.sopt.at.presentation.main.navigation.MainBottomNavController
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

//@Composable
//fun MainScreen(
//    navigator: MainBottomNavController,
//    modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            BottomNavigationBar(
//                modifier = Modifier
//                    .background(Color.Black)
//                    .navigationBarsPadding(),
//                tabs = MainBottomTab.entries,
//                currentTab = navigator.currentTab,
//                onTabSelected = { navigator.navigate(it) }
//            )
//        }
//    ) {innerPadding ->
//        MainBottomNavHost(
//            navigator = navController,
//            paddingValues = innerPadding
//        )
//    }
//}
