package org.sopt.at.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import org.sopt.at.presentation.home.HomeContents
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun HomeBasicSection(
    sectionTitle: String,
    contents: ImmutableList<HomeContents>,
    contentPaddingValues: PaddingValues,
    horizontalArrangement: Arrangement.Horizontal,
    modifier: Modifier = Modifier
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
            contents = contents
        )
    }
}

@Composable
private fun SectionTitleBar(
    title: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {
    Row(modifier = modifier.padding(contentPadding)) {
        Text(
            text = title,
            color = TvingTheme.colors.BasicWhite,
            fontSize = 18.sp,
            style = TvingTheme.typography.title
        )
    }
}

@Composable
private fun SectionLazyRow(
    contentPaddingValues: PaddingValues,
    horizontalArrangement: Arrangement.Horizontal,
    contents: ImmutableList<HomeContents>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = contentPaddingValues,
        horizontalArrangement = horizontalArrangement,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f)
    ) {
        items(contents, key = { it.id }) { content ->
            Image(
                painter = painterResource(id = content.image),
                contentDescription = content.contentDescription,
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
        }
    }
}