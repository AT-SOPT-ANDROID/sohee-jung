package org.sopt.at.presentation.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.Route
import org.sopt.at.presentation.signup.SignUpId
import org.sopt.at.presentation.signup.SignUpIdRoute
import org.sopt.at.presentation.signup.SignUpNickName
import org.sopt.at.presentation.signup.SignUpNickNameRoute
import org.sopt.at.presentation.signup.SignUpPassword
import org.sopt.at.presentation.signup.SignUpPasswordRoute
import org.sopt.at.presentation.signup.SignUpScreen
import org.sopt.at.presentation.signup.SignUpViewModel

@Serializable
data object SignUp : Route

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    navigate(SignUp, navOptions)
}

fun NavGraphBuilder.signUpNavGraph(
    navigateToSignIn: () -> Unit,
) {
    composable<SignUp> {
        SignUpScreen(
            navigateToSignIn = navigateToSignIn
        )
    }
}

fun NavGraphBuilder.signUpGraph(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: SignUpViewModel,
    snackbarHostState: SnackbarHostState,
    navigateToSignIn: () -> Unit
) {
    composable<SignUpId> {
        SignUpIdRoute(
            onSignUpIdButtonClickSuccess = {
                navController.navigate(SignUpPassword)
            },
            onBackButtonClick = {},
            viewModel = viewModel,
            snackbarHostState = snackbarHostState,
            paddingValues = paddingValues
        )
    }

    composable<SignUpPassword> {
        SignUpPasswordRoute(
            onSignUpPasswordButtonClickSuccess = {
                navController.navigate(SignUpNickName)
            },
            onBackButtonClick = {},
            viewModel = viewModel,
            snackbarHostState = snackbarHostState,
            paddingValues = paddingValues
        )
    }

    composable<SignUpNickName> {
        SignUpNickNameRoute(
            onNicknameButtonClickSuccess = navigateToSignIn,
            viewModel = viewModel,
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
            onBackButtonClick = {}
        )
    }
}