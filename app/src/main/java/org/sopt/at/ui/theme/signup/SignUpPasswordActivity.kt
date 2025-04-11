package org.sopt.at.ui.theme.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.GrayButtonLine
import org.sopt.at.ui.theme.GrayEdit
import org.sopt.at.ui.theme.GrayHintText
import org.sopt.at.ui.theme.ShowHidePasswordTextField
import org.sopt.at.ui.theme.signin.SignInActivity

class SignUpPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                ATSOPTANDROIDTheme {
                    SignUpPassword()
                }
            }
        }
    }
}

@Composable
fun SignUpPassword() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = SnackbarHostState()
    var password by remember { mutableStateOf("") }
    var id = remember {
        (context as? Activity)?.intent?.getStringExtra("id") ?: ""
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .padding(start = 6.dp, top = 6.dp)
            ) {
                IconButton(onClick = {
                    (context as Activity).finish()
                }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "뒤로가기",
                        tint = Color.White
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "비밀번호를 입력해주세요.",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontSize = 20.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                ShowHidePasswordTextField(
                    password = password,
                    onPasswordChange = { password = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "영문, 숫자, 특수문자(~!@#$%^&*) 조합 8~15자리",
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    color = GrayHintText
                )

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val errorMessage = when {
                        password.length < 8 -> "비밀번호는 8자 이상으로 입력해주세요."
                        password.length > 15 -> "비밀번호는 15자 이하로 입력해주세요."
                        !password.matches(Regex(".*[a-zA-Z0-9].*")) || !password.matches(Regex(".*[!@#^&*()].*"))
                            -> "비밀번호 입력 형식을 맞춰주세요."

                        else -> null
                    }
                    if (errorMessage != null) {
                        scope.launch {
                            snackbarHostState.showSnackbar(errorMessage)
                        }
                    } else {
                        val intent = Intent().apply {
                            putExtra("id", id)
                            putExtra("password", password)
                        }
                        (context as Activity).apply {
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color.Black),
                border = BorderStroke(2.dp, GrayButtonLine)
            ) {
                Text(
                    text = "다음"
                )
            }
        }
    }


}


@Preview
@Composable
fun SignUpPasswordPreview() {
    SignUpPassword()
}