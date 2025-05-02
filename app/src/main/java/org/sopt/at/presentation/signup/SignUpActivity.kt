package org.sopt.at.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import org.sopt.at.presentation.signin.SignInActivity
import org.sopt.at.presentation.signup.SignUpViewModel.Companion.validateId
import org.sopt.at.presentation.signup.SignUpViewModel.Companion.validatePassword
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel by viewModels<SignUpViewModel>()

        setContent {
            ATSOPTANDROIDTheme {
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                val uiState by viewModel.uiState.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Black,
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) { innerPadding ->
                    if (uiState.isIdScreen) {
                        SignUpIdScreen(
                            onBackButtonClick = {},
                            paddingValues = innerPadding,
                            onSignUpIdButtonClick = {
                                val errorMessage = validateId(uiState.id)
                                if (errorMessage != null) {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(errorMessage)
                                    }
                                } else {
                                    viewModel.updateIdScreen()
                                }
                            },
                            viewModel = viewModel
                        )
                    } else {
                        SignUpPasswordScreen(
                            onBackButtonClick = {},
                            paddingValues = innerPadding,
                            onSignUpPasswordButtonClick = {
                                val errorMessage = validatePassword(uiState.password)
                                if (errorMessage != null) {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(errorMessage)
                                    }
                                } else {
                                    val intent = Intent(context, SignInActivity::class.java)
                                    intent.apply {
                                        putExtra("id", uiState.id)
                                        putExtra("password", uiState.password)
                                        setResult(RESULT_OK, this)
                                    }
                                    finish()
                                }
                            },
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}