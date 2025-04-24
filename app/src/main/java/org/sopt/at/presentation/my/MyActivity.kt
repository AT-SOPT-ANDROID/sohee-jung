package org.sopt.at.presentation.my

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.util.KeyUtil.ID

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userId = intent.getStringExtra(ID) ?: ""

        setContent {
            ATSOPTANDROIDTheme(darkTheme = true) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Black
                ) { innerPadding ->
                    MyScreen(
                        onBackButtonClick = { },
                        userId = userId,
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun MyPagePreview() {
    MyScreen(
        onBackButtonClick = { },
        userId = "dd",
        paddingValues = PaddingValues()
    )
}