package org.sopt.at.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.component.AtSoptPasswordTextField
import org.sopt.at.component.button.AtSoptButton
import org.sopt.at.component.textfield.AtSoptTextField
import org.sopt.at.component.topbar.AtSoptOnBoardingTopBar
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.GrayButton
import org.sopt.at.ui.theme.GrayExtraText
import org.sopt.at.ui.theme.GrayLine

@Composable
fun SignUpIdScreen(
    onBackButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    onSignUpIdButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
            .imePadding()
    ) {
        AtSoptOnBoardingTopBar(onBackButtonClick = onBackButtonClick)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "아이디를 입력해주세요.",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptTextField(
            value = uiState.id,
            onValueChange = { viewModel.updateId(it) },
            placeholder = "아이디",
            backgroundColor = GrayButton,
            backgroundFocusedColor = GrayButton,
            borderFocusedColor = Color.White

        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "영문 소문자 또는 영문 대문자, 숫자 조합 6~12자리",
            color = GrayExtraText,
            fontSize = 12.sp
        )

        Spacer(modifier.weight(1f))

        AtSoptButton(
            text = "다음",
            onClick = onSignUpIdButtonClick,
            textColor = Color.White,
            textConfirmColor = Color.Black,
            backgroundColor = Color.Transparent,
            backgroundConfirmColor = Color.White,
            modifier = modifier,
            isValid = uiState.id.isNotEmpty(),
            borderColor = GrayLine
        )


    }
}

@Composable
fun SignUpPasswordScreen(
    onBackButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    onSignUpPasswordButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .background(Color.Black)
            .padding(20.dp)
            .imePadding()
    ) {
        AtSoptOnBoardingTopBar(onBackButtonClick = onBackButtonClick)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "비밀번호를 입력해주세요",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptPasswordTextField(
            value = uiState.password,
            placeholder = "비밀번호",
            onPasswordChange = { viewModel.updatePassword(it) },
            isPasswordVisibility = uiState.passwordVisibility,
            onTogglePasswordVisibility = { viewModel.updatePasswordVisibility(!uiState.passwordVisibility) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "영문, 숫자, 특수문자(~!@#\$%^&*) 조합 8~15자리",
            color = GrayExtraText,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        AtSoptButton(
            text = "다음",
            onClick = onSignUpPasswordButtonClick,
            textColor = Color.White,
            textConfirmColor = Color.Black,
            backgroundColor = Color.Transparent,
            backgroundConfirmColor = Color.White,
            modifier = modifier,
            isValid = uiState.password.isNotEmpty(),
            borderColor = GrayLine
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SignupIdPreview() {
    ATSOPTANDROIDTheme {
        SignUpIdScreen(
            onBackButtonClick = {},
            paddingValues = PaddingValues(),
            onSignUpIdButtonClick = {},
            viewModel = SignUpViewModel()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPasswordPreview() {
    ATSOPTANDROIDTheme {
        SignUpPasswordScreen(
            onBackButtonClick = {},
            paddingValues = PaddingValues(),
            onSignUpPasswordButtonClick = {},
            viewModel = SignUpViewModel()
        )
    }

}