package org.sopt.at.presentation.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.sopt.at.component.button.AtSoptButton
import org.sopt.at.component.topbar.AtSoptOnBoardingTopBar
import org.sopt.at.local.datastore.UserLocalDataStore
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun MyScreen(
    onLogoutButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val viewModel = remember { MyViewModel(UserLocalDataStore(context)) }
    val nickNameState = viewModel.nickname.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMyNickname()
    }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(color = TvingTheme.colors.BasicBlack)
            .padding(horizontal = 20.dp)
            .imePadding()
    ) {
        AtSoptOnBoardingTopBar(onBackButtonClick = onBackButtonClick)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "닉네임 ${nickNameState.value.nickName}",
            modifier = Modifier
                .padding(10.dp),
            color = TvingTheme.colors.BasicWhite
        )

        Spacer(modifier = Modifier.weight(1f))

        AtSoptButton(
            text = "로그아웃",
            onClick = onLogoutButtonClick,
            textColor = TvingTheme.colors.BasicWhite,
            textConfirmColor = TvingTheme.colors.BasicWhite,
            backgroundColor = TvingTheme.colors.BasicBlack,
            backgroundConfirmColor = TvingTheme.colors.BasicBlack,
            borderColor = TvingTheme.colors.Gray3,
            borderConfirmColor = TvingTheme.colors.Gray3
        )
    }
}