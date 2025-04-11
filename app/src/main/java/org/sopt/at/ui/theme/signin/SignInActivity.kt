package org.sopt.at.ui.theme.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.ui.common.noRippleClickable
import org.sopt.at.ui.theme.my.MyActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.GrayButton
import org.sopt.at.ui.theme.GrayEdit
import org.sopt.at.ui.theme.GrayEditText
import org.sopt.at.ui.theme.GrayHintText
import org.sopt.at.ui.theme.GrayText
import org.sopt.at.ui.theme.ShowHidePasswordTextField
import org.sopt.at.ui.theme.signup.SignUpIdActivity

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                ATSOPTANDROIDTheme {
                    SignIn()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn() {
    // for intent
    val context = LocalContext.current
    // for snackbar
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    // for textfield
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .padding(start = 6.dp, top = 6.dp, bottom = 6.dp)
            ) {
                IconButton(onClick = {
                    // 뒤로가기 버튼 눌렀을 때
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
                .background(color = Color.Black),
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "TVING ID 로그인",
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = id,
                onValueChange = { id = it },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(55.dp),
                placeholder = {
                    Text(
                        text = "아이디"
                    )
                },
                singleLine = true, // TextField에서 한줄만 표시하도록 하는 설정
                shape = RoundedCornerShape(5.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = GrayHintText,
                    unfocusedTextColor = GrayHintText,
                    focusedPlaceholderColor = GrayHintText,
                    unfocusedPlaceholderColor = GrayHintText,
                    focusedContainerColor = GrayEdit,
                    unfocusedContainerColor = GrayEdit,
                    // 하단 밑줄 사라지게
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // show-hide password TextField
            ShowHidePasswordTextField(
                password = password,
                onPasswordChange = { password = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (true) {
                        val intent = Intent(context, MyActivity::class.java).apply {
                            flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        context.startActivity(intent)
                    } else {
                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    message = ""
                                )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth() // fillMaxWidth()를 사용하고 있는 가운데 정렬 필요 없음
                    .height(60.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GrayButton,
                    contentColor = GrayHintText
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("로그인하기")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "아이디 찾기",
                    color = GrayText,
                    modifier = Modifier.noRippleClickable {
                        // 아이디 찾기 화면
                    })

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = "|",
                    color = GrayText
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = "비밀번호 찾기",
                    color = GrayText,
                    modifier = Modifier.noRippleClickable {
                        // 비밀번호 찾기 화면
                    }
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = "|",
                    color = GrayText
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(text = "회원가입",
                    color = GrayText,
                    modifier = Modifier.noRippleClickable {
                        val intent = Intent(context, SignUpIdActivity::class.java).apply {
                            flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun SignInPreview() {
    SignIn()
}