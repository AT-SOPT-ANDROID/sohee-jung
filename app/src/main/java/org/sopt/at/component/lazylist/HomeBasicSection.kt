package org.sopt.at.component.lazylist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.presentation.home.HomeContentsEntity

@Composable
fun HomeBasicSection(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    contents: List<HomeContentsEntity>,
    contentPaddingValues: PaddingValues,
    horizontalArrangement: Arrangement.Horizontal,
    contentDescription: String = ""
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SectionTitleBar(
            title = sectionTitle,
            contentPadding = contentPaddingValues
        )

        Spacer(modifier = Modifier.height(5.dp))

        SectionLazyRow(
            contentPaddingValues = contentPaddingValues,
            horizontalArrangement = horizontalArrangement,
            contentDescription = contentDescription,
            contents = contents,
        )

    }
}

@Composable
fun SectionTitleBar(
    title: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {
    Row(modifier = modifier
        .padding(contentPadding)) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun SectionLazyRow(
    contentPaddingValues: PaddingValues,
    horizontalArrangement: Arrangement.Horizontal,
    contentDescription: String = "",
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    contents: List<HomeContentsEntity>
) {
    LazyRow(
        contentPadding = contentPaddingValues,
        horizontalArrangement = horizontalArrangement,
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        items(contents) { content ->
            Image(
                painter = painterResource(id = content.image),
                contentDescription = contentDescription,
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
        }
    }
}