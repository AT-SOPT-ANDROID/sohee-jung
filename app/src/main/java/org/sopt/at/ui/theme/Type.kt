package org.sopt.at.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import org.sopt.at.R

val tvingFontBold = FontFamily(Font(R.font.pretendard_bold))
val tvingFontRegular = FontFamily(Font(R.font.pretendard_regular))
val tvingFontSemiBold = FontFamily(Font(R.font.pretendard_semibold))

data class TvingTypography(
    val title: TextStyle,
    val subTitle: TextStyle,
    val body: TextStyle,
    val button: TextStyle,
    val caption: TextStyle
)

val defaultTvingTypography = TvingTypography(
    title = TextStyle(
        fontFamily = tvingFontBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    subTitle = TextStyle(
        fontFamily = tvingFontSemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    body = TextStyle(
        fontFamily = tvingFontRegular,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    button = TextStyle(
        fontFamily = tvingFontBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    caption = TextStyle(
        fontFamily = tvingFontRegular,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)

val LocalTvingTypographyProvider = staticCompositionLocalOf { defaultTvingTypography }