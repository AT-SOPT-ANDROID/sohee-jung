package org.sopt.at.presentation.my

import android.content.Intent
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import org.sopt.at.component.button.AtSoptButton
import org.sopt.at.component.topbar.AtSoptOnBoardingTopBar
import org.sopt.at.navigation.Route
import org.sopt.at.presentation.signin.SignInActivity
import org.sopt.at.ui.theme.GrayLine

@Serializable
data object MyRoute: Route

@Composable
fun MyScreen(
    onBackButtonClick: () -> Unit,
    userId: String,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(20.dp)
            .imePadding()
    ) {
        AtSoptOnBoardingTopBar(onBackButtonClick = onBackButtonClick)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "이메일 $userId",
            modifier = Modifier
                .padding(10.dp),
            color = Color.White
        )

        Spacer(modifier = Modifier.weight(1f))

        AtSoptButton(
            text = "로그아웃",
            onClick = {
                val intent = Intent(context, SignInActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                context.startActivity(intent)
            },
            textColor = Color.White,
            textConfirmColor = Color.White,
            backgroundColor = Color.Black,
            backgroundConfirmColor = Color.Black,
            borderColor = GrayLine,
            borderConfirmColor = GrayLine
        )
    }
}
