package org.sopt.at.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.sopt.at.common.ValidationResult
import org.sopt.at.component.AtSoptPasswordTextField
import org.sopt.at.component.button.AtSoptButton
import org.sopt.at.component.topbar.AtSoptOnBoardingTopBar
import org.sopt.at.navigation.Route
import org.sopt.at.ui.theme.TVINGTheme
import org.sopt.at.ui.theme.TvingTheme

@Serializable
data object SignUpPassword : Route

@Composable
fun SignUpPasswordRoute(
    onSignUpPasswordButtonClickSuccess: () -> Unit,
    onBackButtonClick: () -> Unit,
    viewModel: SignUpViewModel,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    val isButtonEnabled by remember {
        derivedStateOf { uiState.password.isNotEmpty() }
    }

    SignUpPasswordScreen(
        userPassword = uiState.password,
        onPasswordChange = viewModel::updatePassword,
        isValid = isButtonEnabled,
        onBackButtonClick = onBackButtonClick,
        paddingValues = paddingValues,
        onSignUpPasswordButtonClick = {
            val result = viewModel.validatePassword()

            when (result) {
                is ValidationResult.Error -> {
                    val errorMessage = result.message
                    scope.launch {
                        snackbarHostState.showSnackbar(errorMessage)
                    }
                }

                is ValidationResult.Success -> {
                    onSignUpPasswordButtonClickSuccess()
                }
            }
        },
        isPasswordVisibility = uiState.passwordVisibility,
        onTogglePasswordVisibility = {
            viewModel.updatePasswordVisibility(!uiState.passwordVisibility)
        }
    )
}

@Composable
fun SignUpPasswordScreen(
    userPassword: String,
    onPasswordChange: (String) -> Unit,
    isValid: Boolean,
    onBackButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    onSignUpPasswordButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPasswordVisibility: Boolean = false,
    onTogglePasswordVisibility: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .background(TvingTheme.colors.BasicBlack)
            .padding(20.dp)
            .imePadding()
    ) {
        AtSoptOnBoardingTopBar(onBackButtonClick = onBackButtonClick)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "비밀번호를 입력해주세요",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = TvingTheme.colors.BasicWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptPasswordTextField(
            value = userPassword,
            placeholder = "비밀번호",
            onPasswordChange = onPasswordChange,
            isPasswordVisibility = isPasswordVisibility,
            onTogglePasswordVisibility = onTogglePasswordVisibility
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "영문, 숫자, 특수문자(~!@#\$%^&*) 조합 8~15자리",
            color = TvingTheme.colors.Gray2,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        AtSoptButton(
            text = "다음",
            onClick = onSignUpPasswordButtonClick,
            textColor = TvingTheme.colors.BasicWhite,
            textConfirmColor = TvingTheme.colors.BasicBlack,
            backgroundColor = Color.Transparent,
            backgroundConfirmColor = TvingTheme.colors.BasicWhite,
            modifier = modifier,
            isValid = isValid,
            borderColor = TvingTheme.colors.Gray3
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPasswordPreview() {
    TVINGTheme {
        SignUpPasswordScreen(
            onBackButtonClick = {},
            paddingValues = PaddingValues(),
            onSignUpPasswordButtonClick = {},
            userPassword = "",
            onPasswordChange = {},
            isValid = true,
            isPasswordVisibility = false,
            onTogglePasswordVisibility = {}
        )
    }
}