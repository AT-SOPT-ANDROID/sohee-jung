package org.sopt.at.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.common.noRippleClickable
import org.sopt.at.component.AtSoptPasswordTextField
import org.sopt.at.component.button.AtSoptButton
import org.sopt.at.component.textfield.AtSoptTextField
import org.sopt.at.component.topbar.AtSoptBasicTopBar
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.GrayButton
import org.sopt.at.ui.theme.GrayEdit
import org.sopt.at.ui.theme.GrayExtraText
import org.sopt.at.ui.theme.GrayText
import org.sopt.at.ui.theme.RedButton


@Composable
fun SignInScreen(
    onBackButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    onSignInButtonClick: () -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(20.dp)
            .background(color = Color.Black)
            .imePadding()
    ) {
        AtSoptBasicTopBar(onBackButtonClick = onBackButtonClick)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "TVING ID 로그인",
            modifier = Modifier
                .padding(horizontal = 20.dp),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptTextField(
            value = uiState.id,
            placeholder = "아이디",
            onValueChange = { viewModel.updateId(it) },
            backgroundColor = GrayEdit,
            backgroundFocusedColor = GrayEdit,
            borderFocusedColor = Color.White,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        AtSoptPasswordTextField(
            value = uiState.password,
            placeholder = "비밀번호",
            onPasswordChange = { viewModel.updatePassword(it) },
            isPasswordVisibility = uiState.passwordVisibility,
            onTogglePasswordVisibility = { viewModel.updatePasswordVisibility(!uiState.passwordVisibility) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptButton(
            text = "로그인하기",
            onClick = onSignInButtonClick,
            textColor = GrayExtraText,
            textConfirmColor = Color.White,
            backgroundColor = GrayButton,
            backgroundConfirmColor = RedButton,
            isValid = uiState.id.isNotEmpty() && uiState.password.isNotEmpty()

        )

        Spacer(modifier = Modifier.height(32.dp))

        SignInTextButton(
            onSignUpButtonClick = onSignUpButtonClick
        )

        Spacer(modifier = Modifier.height(32.dp))

        SignInDescription()
    }
}


@Composable
fun SignInTextButton(
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "아이디 찾기",
            color = GrayText,
            modifier = Modifier.noRippleClickable {
                // TODO
            }
        )

        Spacer(modifier = Modifier.width(15.dp))

        VerticalDivider(
            modifier = Modifier
                .height(15.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = "비밀번호 찾기",
            color = GrayText,
            modifier = Modifier.noRippleClickable {
                // TODO
            }
        )

        Spacer(modifier = Modifier.width(15.dp))

        VerticalDivider(
            modifier = Modifier
                .height(15.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = "회원가입",
            color = GrayText,
            modifier = Modifier.noRippleClickable {
                onSignUpButtonClick()
            }
        )

    }
}

@Composable
fun SignInDescription() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = buildAnnotatedString {
                append("이 사이트는 Google reCAPTCHA로 보호되며,\n")
                withStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Google 개인정보 처리방침")
                }
                append("과")
                withStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("서비스 약관")
                }
                append("이 적용됩니다.")
            },
            color = GrayExtraText,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    ATSOPTANDROIDTheme {
        SignInScreen(
            onBackButtonClick = {},
            paddingValues = PaddingValues(0.dp),
            onSignInButtonClick = {},
            onSignUpButtonClick = {},
            viewModel = SignInViewModel()
        )
    }
}