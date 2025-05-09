package org.sopt.at.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import org.sopt.at.presentation.signup.component.ValidationResult
import org.sopt.at.component.button.AtSoptButton
import org.sopt.at.component.textfield.AtSoptTextField
import org.sopt.at.component.topbar.AtSoptOnBoardingTopBar
import org.sopt.at.navigation.Route
import org.sopt.at.ui.theme.Gray4
import org.sopt.at.ui.theme.TVINGTheme
import org.sopt.at.ui.theme.TvingTheme

@Serializable
data object SignUpId : Route

@Composable
fun SignUpIdRoute(
    onSignUpIdButtonClickSuccess: () -> Unit,
    onBackButtonClick: () -> Unit,
    viewModel: SignUpViewModel,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    val isButtonEnabled by remember {
        derivedStateOf { uiState.id.isNotEmpty() }
    }

    SignUpIdScreen(
        userId = uiState.id,
        onUserIdChange = viewModel::updateId,
        isValid = isButtonEnabled,
        onSignUpIdButtonClick = {
            val result = viewModel.validateId()

            when (result) {
                is ValidationResult.Error -> {
                    val errorMessage = result.message
                    scope.launch {
                        snackbarHostState.showSnackbar(errorMessage)
                    }
                }

                is ValidationResult.Success -> {
                    onSignUpIdButtonClickSuccess()
                }
            }
        },
        onBackButtonClick = onBackButtonClick,
        paddingValues = paddingValues
    )

}

@Composable
fun SignUpIdScreen(
    userId: String,
    onUserIdChange: (String) -> Unit,
    isValid: Boolean,
    onSignUpIdButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(TvingTheme.colors.BasicBlack)
            .padding(20.dp)
            .imePadding()
    ) {
        AtSoptOnBoardingTopBar(onBackButtonClick = onBackButtonClick)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "아이디를 입력해주세요.",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = TvingTheme.colors.BasicWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptTextField(
            value = userId,
            onValueChange = onUserIdChange,
            placeholder = "아이디",
            backgroundColor = TvingTheme.colors.Gray4,
            backgroundFocusedColor = Gray4,
            borderFocusedColor = TvingTheme.colors.BasicWhite
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "영문 소문자 또는 영문 대문자, 숫자 조합 6~12자리",
            color = TvingTheme.colors.Gray2,
            fontSize = 12.sp
        )

        Spacer(modifier.weight(1f))

        AtSoptButton(
            text = "다음",
            onClick = onSignUpIdButtonClick,
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
private fun SignupIdPreview() {
    TVINGTheme {
        SignUpIdScreen(
            userId = "",
            onUserIdChange = {},
            isValid = false,
            onSignUpIdButtonClick = {},
            onBackButtonClick = {},
            paddingValues = PaddingValues()
        )
    }
}