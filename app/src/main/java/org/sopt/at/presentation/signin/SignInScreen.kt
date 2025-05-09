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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch
import org.sopt.at.common.noRippleClickable
import org.sopt.at.component.AtSoptPasswordTextField
import org.sopt.at.component.button.AtSoptButton
import org.sopt.at.component.textfield.AtSoptTextField
import org.sopt.at.component.topbar.AtSoptOnBoardingTopBar
import org.sopt.at.local.datastore.UserLocalDataStore
import org.sopt.at.ui.theme.TVINGTheme
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun SignInRoute(
    onBackButtonClick: () -> Unit,
    onSignInButtonClickSuccess: () -> Unit,
    onSignUpButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current
    val viewModel = remember {
        SignInViewModel(UserLocalDataStore(context))
    }
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    val isButtonEnabled by remember {
        derivedStateOf { uiState.id.isNotEmpty() && uiState.password.isNotEmpty() }
    }

    SignInScreen(
        userId = uiState.id,
        userPassword = uiState.password,
        onIdChange = viewModel::updateId,
        onPasswordChange = viewModel::updatePassword,
        onBackButtonClick = onBackButtonClick,
        isValid = isButtonEnabled,
        onSignInButtonClick = {
            viewModel.signIn(
                onSuccess = onSignInButtonClickSuccess,
                onFailure = { message ->
                    scope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                })
        },
        onSignUpButtonClick = onSignUpButtonClick,
        paddingValues = paddingValues,
        isPasswordVisibility = uiState.passwordVisibility,
        onTogglePasswordVisibility = {
            viewModel.updatePasswordVisibility(!uiState.passwordVisibility)
        }
    )
}

@Composable
fun SignInScreen(
    userId: String,
    userPassword: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackButtonClick: () -> Unit,
    isValid: Boolean,
    onSignInButtonClick: () -> Unit,
    onSignUpButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    isPasswordVisibility: Boolean = false,
    onTogglePasswordVisibility: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(20.dp)
            .background(color = TvingTheme.colors.BasicBlack)
            .imePadding()
    ) {
        AtSoptOnBoardingTopBar(onBackButtonClick = onBackButtonClick)

        TitleSection()

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptTextField(
            value = userId,
            placeholder = "아이디",
            onValueChange = onIdChange,
            backgroundColor = TvingTheme.colors.Gray5,
            backgroundFocusedColor = TvingTheme.colors.Gray5,
            borderFocusedColor = TvingTheme.colors.BasicWhite,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        AtSoptPasswordTextField(
            value = userPassword,
            placeholder = "비밀번호",
            onPasswordChange = onPasswordChange,
            isPasswordVisibility = isPasswordVisibility,
            onTogglePasswordVisibility = onTogglePasswordVisibility
        )

        Spacer(modifier = Modifier.height(24.dp))

        AtSoptButton(
            text = "로그인하기",
            onClick = onSignInButtonClick,
            textColor = TvingTheme.colors.Gray2,
            textConfirmColor = TvingTheme.colors.BasicWhite,
            backgroundColor = TvingTheme.colors.Gray4,
            backgroundConfirmColor = TvingTheme.colors.BrandRed,
            isValid = isValid

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
private fun TitleSection() {
    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = "TVING ID 로그인",
        modifier = Modifier
            .padding(horizontal = 20.dp),
        color = TvingTheme.colors.BasicWhite,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun SignInTextButton(
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
            color = TvingTheme.colors.Gray1,
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
            color = TvingTheme.colors.Gray1,
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
            color = TvingTheme.colors.Gray1,
            modifier = Modifier.noRippleClickable {
                onSignUpButtonClick()
            }
        )

    }
}

@Composable
private fun SignInDescription() {
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
            color = TvingTheme.colors.Gray2,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    TVINGTheme {
        SignInScreen(
            userId = "",
            userPassword = "",
            onIdChange = {},
            onPasswordChange = {},
            onBackButtonClick = {},
            isValid = false,
            paddingValues = PaddingValues(0.dp),
            onSignInButtonClick = {},
            onSignUpButtonClick = {},
        )
    }
}