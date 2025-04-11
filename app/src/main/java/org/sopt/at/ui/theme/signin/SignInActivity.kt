package org.sopt.at.ui.theme.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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
import org.sopt.at.ui.theme.RedButton
import org.sopt.at.ui.theme.ShowHidePasswordTextField
import org.sopt.at.ui.theme.signup.SignUpIdActivity

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                ATSOPTANDROIDTheme {
                    SignInScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen() {
    // for intent
    val context = LocalContext.current
    // for snackbar
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    // for textfield
    var registeredId by remember { mutableStateOf("") }
    var registeredPassword by remember { mutableStateOf("") }

    var inputId by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                registeredId = it.getStringExtra("id") ?: ""
                registeredPassword = it.getStringExtra("password") ?: ""
            }
        }
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
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
                .background(color = Color.Black)
                .imePadding()
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
                value = inputId,
                onValueChange = { inputId = it },
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
                password = inputPassword,
                onPasswordChange = { inputPassword = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            val isValid = inputId.isNotBlank() && inputPassword.isNotBlank()

            Button(
                onClick = {
                    val errorMessage = when {
                        inputId != registeredId && inputPassword == registeredPassword -> "아이디가 틀렸습니다."
                        inputId == registeredId && inputPassword != registeredPassword -> "비밀번호가 틀렸습니다."
                        inputId != registeredId && inputPassword != registeredPassword -> "아이디,비밀번호가 모두 틀렸습니다."
                        else -> null
                    }
                    if (errorMessage != null){
                        scope.launch {
                            snackbarHostState.showSnackbar(errorMessage)
                        }
                    } else{
                        val intent = Intent(context, MyActivity::class.java).apply {
                            putExtra("id", inputId)
                        }
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth() // fillMaxWidth()를 사용하고 있는 가운데 정렬 필요 없음
                    .height(60.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isValid) RedButton else GrayButton,
                    contentColor = if(isValid) Color.White else GrayHintText
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
                        val intent = Intent(context, SignUpIdActivity::class.java)
                        resultLauncher.launch(intent)
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

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
                        ){
                            append("Google 개인정보 처리방침")
                        }
                        append("과 ")
                        withStyle(
                            SpanStyle(
                                textDecoration = TextDecoration.Underline
                            )
                        ){
                            append("서비스 약관")
                        }
                        append("이 적용됩니다.")
                    },
                    color = GrayHintText,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center

                )
            }
        }
    }
}

@Preview
@Composable
fun SignInPreview() {
    SignInScreen()
}