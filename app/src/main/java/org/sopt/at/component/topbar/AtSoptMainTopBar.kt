package org.sopt.at.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.theme.TVINGTheme
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun AtSoptMainTopBar(
    shareTvIconClick: () -> Unit,
    myIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(TvingTheme.colors.BasicBlack)
            .statusBarsPadding()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tving_logo),
            contentDescription = "TVING logo",
            modifier = Modifier
                .height(24.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_maintopbar_share),
            contentDescription = "SearchIcon",
            tint = TvingTheme.colors.BasicWhite,
            modifier = Modifier
                .clickable(onClick = shareTvIconClick)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_my),
            contentDescription = "myIcon",
            tint = Color.Unspecified,
            modifier = Modifier
                .clickable(onClick = myIconClick)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AtSoptMainTopBarPreview() {
    TVINGTheme {
        AtSoptMainTopBar(
            shareTvIconClick = {},
            myIconClick = {}
        )
    }
}