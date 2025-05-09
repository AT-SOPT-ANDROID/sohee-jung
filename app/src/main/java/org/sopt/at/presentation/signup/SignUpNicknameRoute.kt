package org.sopt.at.presentation.signup

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import org.sopt.at.ui.theme.TvingTheme

@Serializable
data object SignUpNickName : Route

@Composable
fun SignUpNickNameRoute(
    onNicknameButtonClickSuccess: () -> Unit,
    viewModel: SignUpViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    onBackButtonClick: () -> Unit
) {
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    val isButtonEnabled by remember {
        derivedStateOf { uiState.nickname.isNotEmpty() }
    }

    SignUpNickNameScreen(
        nickname = uiState.nickname,
        onNicknameChange = viewModel::updateNickname,
        isValid = isButtonEnabled,
        onNicknameButtonClick = {
            val result = viewModel.validateNickname()

            when (result) {
                is ValidationResult.Error -> {
                    val errorMessage = result.message
                    scope.launch {
                        snackbarHostState.showSnackbar(errorMessage)
                    }
                }

                is ValidationResult.Success -> {
                    viewModel.signUp(
                        onSuccess = onNicknameButtonClickSuccess,
                        onFailure = {message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        },
        onBackButtonClick = onBackButtonClick,
        paddingValues = paddingValues
    )
}

@Composable
fun SignUpNickNameScreen(
    nickname: String,
    onNicknameChange: (String) -> Unit,
    onNicknameButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    isValid: Boolean,
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
            text = "닉네임을 입력해주세요.",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = TvingTheme.colors.BasicWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptTextField(
            value = nickname,
            placeholder = "닉네임",
            onValueChange = onNicknameChange,
            backgroundColor = TvingTheme.colors.Gray4,
            backgroundFocusedColor = Gray4,
            borderFocusedColor = TvingTheme.colors.BasicWhite
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "한글, 영어, 숫자 조합 1~20자리",
            color = TvingTheme.colors.Gray2,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        AtSoptButton(
            text = "다음",
            onClick = onNicknameButtonClick,
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
private fun SignUpNickNamePreview() {
    SignUpNickNameScreen(
        nickname = "",
        onNicknameChange = {},
        onBackButtonClick = {},
        onNicknameButtonClick = {},
        paddingValues = PaddingValues(),
        isValid = false
    )
}