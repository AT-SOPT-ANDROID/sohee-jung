package org.sopt.at.presentation.signup

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sopt.at.presentation.signup.navigation.signUpGraph

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    viewModel: SignUpViewModel = viewModel(),
    sharedViewModel: SharedViewModel = viewModel()

) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SignUpId
        ) {
            signUpGraph(
                paddingValues = innerPadding,
                navController = navController,
                viewModel = viewModel,
                sharedViewModel = sharedViewModel,
                snackbarHostState = snackbarHostState,
                navigateToSignIn = navigateToSignIn
            )
        }
    }
}