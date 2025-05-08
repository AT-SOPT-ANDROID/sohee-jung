package org.sopt.at.presentation.home.tabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun SportsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TvingTheme.colors.BasicBlack)
    ) {
        Text(
            text = "SportsScreen"
        )
    }
}

@Preview
@Composable
private fun SportsScreenPreview() {
    SportsScreen()
}