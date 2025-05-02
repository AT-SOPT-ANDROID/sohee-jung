package org.sopt.at.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.TVINGTheme
import org.sopt.at.ui.theme.TvingTheme


@Composable
fun AtSoptTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color,
    backgroundFocusedColor: Color,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Transparent,
    borderFocusedColor: Color = Color.Transparent,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyBoardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusRequester: FocusRequester = FocusRequester(),
    trailingIcon: @Composable (() -> Unit)? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,

        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = if (isFocused) borderFocusedColor else borderColor,
                RoundedCornerShape(5.dp)
            )
            .background(if (isFocused) backgroundFocusedColor else backgroundColor)
            .focusRequester(focusRequester)
            .onFocusChanged { focusState -> isFocused = focusState.isFocused }
            .border(
                width = 1.dp,
                color = if (isFocused) borderFocusedColor else borderColor,
                RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 20.dp),
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyBoardActions,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            color = TvingTheme.colors.BasicWhite,
            fontSize = 16.sp
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .weight(1f)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = TvingTheme.colors.Gray2,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }

                if (trailingIcon != null) {
                    trailingIcon()
                }
            }
        }

    )

}

@Preview(showBackground = true)
@Composable
private fun AtSoptTextFieldPreview() {
    TVINGTheme {
        AtSoptTextField(
            value = "",
            onValueChange = {},
            borderColor = TvingTheme.colors.BasicBlack,
            borderFocusedColor = TvingTheme.colors.BasicBlack,
            backgroundColor = TvingTheme.colors.BasicWhite,
            backgroundFocusedColor = TvingTheme.colors.BasicBlack,
            placeholder = "placeholder"
        )
    }
}