package org.sopt.at.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.ui.theme.TVINGTheme
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun AtSoptOnBoardingTopBar(
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(TvingTheme.colors.BasicBlack)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = "back_button",
            modifier = Modifier
                .clickable(onClick = onBackButtonClick),
            tint = TvingTheme.colors.BasicWhite
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun AtSoptBasicTopBarPreview() {
    TVINGTheme {
        AtSoptOnBoardingTopBar(onBackButtonClick = {})
    }
}