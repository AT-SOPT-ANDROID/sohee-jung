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
fun MovieScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TvingTheme.colors.BasicBlack)
    ) {
        Text(
            text = "MovieScreen"
        )
    }
}

@Preview
@Composable
private fun MovieScreenPreview() {
    MovieScreen()

}