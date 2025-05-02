package org.sopt.at.presentation.shorts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.TVINGTheme
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun ShortsScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TvingTheme.colors.BasicBlack)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Shorts",
            color = TvingTheme.colors.BasicWhite,
            fontSize = 40.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShortsScreenPreview() {
    TVINGTheme {
        ShortsScreen(
            paddingValues = PaddingValues(0.dp)
        )
    }
}