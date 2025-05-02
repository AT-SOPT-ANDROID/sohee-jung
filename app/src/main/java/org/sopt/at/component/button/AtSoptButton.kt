package org.sopt.at.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.GrayLine

@Composable
fun AtSoptButton(
    text: String,
    onClick: () -> Unit,
    textColor: Color,
    textConfirmColor: Color,
    backgroundColor: Color,
    backgroundConfirmColor: Color,
    modifier: Modifier = Modifier,
    isValid: Boolean = false,
    borderColor: Color = Color.Transparent,
    borderConfirmColor: Color = Color.Transparent,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.dp,
                color = if (isValid) borderConfirmColor else borderColor,
                shape = RoundedCornerShape(5.dp)
            )
            .clip(RoundedCornerShape(5.dp))
            .clickable(onClick = onClick, enabled = isValid)
            .background(if (isValid) backgroundConfirmColor else backgroundColor)
            .padding(12.dp)

    ) {
        Text(
            text,
            color = if (isValid) textConfirmColor else textColor
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun AtSoptButtonPreview() {
    ATSOPTANDROIDTheme {
        AtSoptButton(
            text = "다음",
            onClick = {},
            isValid = true,
            textColor = Color.White,
            textConfirmColor = Color.Black,
            borderColor = GrayLine,
            backgroundColor = Color.Transparent,
            backgroundConfirmColor = Color.White
        )
    }
}