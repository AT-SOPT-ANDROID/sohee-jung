package org.sopt.at.presentation.signin.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.Route
import org.sopt.at.presentation.signin.SignInRoute
import org.sopt.at.presentation.signin.SignInScreen

@Serializable
data object SignIn : Route

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) {
    navigate(SignIn, navOptions)
}

fun NavGraphBuilder.signInNavGraph(
    paddingValues: PaddingValues,
    onBackButtonClick: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToSignUpId: () -> Unit
) {
    composable<SignIn> {
        SignInRoute(
            onBackButtonClick = onBackButtonClick,
            paddingValues = paddingValues,
            onSignInButtonClickSuccess = navigateToHome,
            onSignUpButtonClick = navigateToSignUpId
        )
    }
}