package org.sopt.at.presentation.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import org.sopt.at.ui.theme.GrayEditText
import org.sopt.at.ui.theme.GrayHintText

class SignUpIdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                ATSOPTANDROIDTheme {
                    SignUpIdScreen()
                }
            }
        }
    }
}

@Composable
fun SignUpIdScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var id by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val id = result.data?.getStringExtra("id") ?: ""
            val password = result.data?.getStringExtra("password") ?: ""

            val intent = Intent().apply {
                putExtra("id", id)
                putExtra("password", password)
            }
            (context as Activity).setResult(Activity.RESULT_OK, intent)
            (context as Activity).finish()
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
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "아이디를 입력해주세요.",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontSize = 20.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(55.dp),
                    value = id,
                    onValueChange = {
                        id = it
                    },
                    placeholder = {
                        Text(text = "아이디")
                    },
                    singleLine = true,
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


                Text(
                    text = "영문 소문자 또는 영문 대문자, 숫자 조합 6~12가지",
                    modifier = Modifier
                        .padding(20.dp),
                    color = GrayHintText
                )
            }

            Spacer(Modifier.weight(1f))

            val isValid = id.isNotBlank()

            Button(
                onClick = {
                    val errorMessage = when {
                        id.length < 6 -> "아이디는 6자 이상으로 입력해주세요."
                        id.length > 12 -> "아이디는 12자 이하로 입력해주세요."
                        !id.matches(Regex("^[a-zA-Z0-9]*$")) -> "아이디는 영문자와 숫자만 입력해주세요."
                        !id.contains(Regex("[a-zA-Z]")) -> "아이디에 영문자를 최소 한 글자 포함해주세요."
                        !id.contains(Regex("[0-9]")) -> "아이디에 숫자를 최소 한 글자 포함해주세요."
                        else -> null
                    }

                    if (errorMessage != null) {
                        scope.launch {
                            snackbarHostState.showSnackbar(errorMessage)
                        }
                    } else {
                        val intent = Intent(context, SignUpPasswordActivity::class.java).apply {
                            putExtra("id", id)
                        }
                        launcher.launch(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(isValid) Color.White else Color.Black,
                    contentColor = if (isValid) Color.Black else Color.White
                ),
                border = BorderStroke(2.dp, GrayButtonLine), // 버튼 stroke 설정
                shape = RoundedCornerShape(5.dp)

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
fun SignUpIdPreview() {
    SignUpIdScreen()
}