package org.sopt.at.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.component.textfield.AtSoptTextField
import org.sopt.at.ui.theme.GrayEdit

@Composable
fun AtSoptPasswordTextField(
    value: String,
    placeholder: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisibility: Boolean = false,
    onTogglePasswordVisibility: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    AtSoptTextField(
        value = value,
        placeholder = placeholder,
        onValueChange = onPasswordChange,
        backgroundColor = GrayEdit,
        backgroundFocusedColor = GrayEdit,
        borderFocusedColor = Color.White,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyBoardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        visualTransformation = if (isPasswordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon =  {
            Icon(
                imageVector = if (isPasswordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                contentDescription = "비밀번호 보이기",
                modifier = Modifier.clickable {
                    onTogglePasswordVisibility()
                },
                tint = Color.Gray
            )
        }

    )
}

@Preview
@Composable
private fun AtSoptPasswordTextFieldPreview() {
    AtSoptPasswordTextField(
        value = "",
        placeholder = "비밀번호",
        onPasswordChange = {},
    )
}