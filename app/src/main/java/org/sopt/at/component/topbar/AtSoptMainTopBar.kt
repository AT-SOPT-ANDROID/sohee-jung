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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.TvingLogo

@Composable
fun AtSoptMainTopBar(
    modifier: Modifier = Modifier,
    shareTvIconClick: () -> Unit = {},
    myIconClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_tving_logo),
            contentDescription = "TVING logo",
            modifier = Modifier
                .height(24.dp)
        )

        Row(){
            Icon(
                painter = painterResource(id = R.drawable.ic_maintopbar_share),
                contentDescription = "SearchIcon",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        shareTvIconClick()
                    }
            )

            Spacer(modifier = Modifier.width(15.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_my),
                contentDescription = "myIcon",
                tint = TvingLogo,
                modifier = Modifier
                    .clickable {
                        myIconClick()
                    }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AtSoptMainTopBarPreview() {
    ATSOPTANDROIDTheme {
        AtSoptMainTopBar()
    }
}