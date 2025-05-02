package org.sopt.at.presentation.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeContents(
    val id: Int,
    val image: Int,
    val contentDescription: String = ""
)