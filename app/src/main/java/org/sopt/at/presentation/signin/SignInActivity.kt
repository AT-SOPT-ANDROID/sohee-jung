package org.sopt.at.presentation.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import org.sopt.at.presentation.my.MyActivity
import org.sopt.at.presentation.signup.SignUpActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        var registeredId: String = ""
        var registeredPassword: String = ""

        val viewModel by viewModels<SignInViewModel>()

        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let {
                    registeredId = it.getStringExtra("id") ?: ""
                    registeredPassword = it.getStringExtra("password") ?: ""
                }
            }
        }

        setContent {
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }

            val uiState by viewModel.uiState.collectAsState()

            ATSOPTANDROIDTheme(darkTheme = true) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Black,
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) { innerPadding ->
                    SignInScreen(
                        onBackButtonClick = { },
                        paddingValues = innerPadding,
                        onSignInButtonClick = {
                            val errorMessage = estimationErrorAtSignIn(
                                uiState.id,
                                uiState.password,
                                registeredId,
                                registeredPassword
                            )

                            if (errorMessage != null) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(errorMessage)
                                }
                            } else {
//                                val intent = Intent(context, MyActivity::class.java).apply {
//                                    putExtra("id", uiState.id)
//                                }
//                                context.startActivity(intent)
                            }

                        },
                        onSignUpButtonClick = {
                            val intent = Intent(context, SignUpActivity::class.java)
                            resultLauncher.launch(intent)
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


fun estimationErrorAtSignIn(
    inputId: String,
    inputPassword: String,
    registeredId: String,
    registeredPassword: String
): String? {
    val errorMessage = when {
        inputId != registeredId && inputPassword == registeredPassword -> "아이디가 틀렸습니다."
        inputId == registeredId && inputPassword != registeredPassword -> "비밀번호가 틀렸습니다."
        inputId != registeredId && inputPassword != registeredPassword -> "아이디, 비밀번호가 모두 틀렸습니다."
        else -> null
    }

    return errorMessage
}