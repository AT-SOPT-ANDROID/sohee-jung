package org.sopt.at.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.ImmutableList
import org.sopt.at.presentation.home.component.HomeBasicSection
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(TvingTheme.colors.BasicBlack)
            .padding(paddingValues)
    ) {
        item {
            TopBanner(viewModel.contents)
        }

        item {
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            Text(
                text = "오늘의 티빙 TOP 20",
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                color = TvingTheme.colors.BasicWhite,
                fontSize = 18.sp,
                style = TvingTheme.typography.title
            )

            Spacer(modifier = Modifier.height(5.dp))

            Ranking20(viewModel.contents)
        }

        item {
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            OnBoarding(viewModel.contents)
        }
    }
}

@Composable
private fun TopBanner(contents: ImmutableList<HomeContents>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .padding(top = 20.dp)
    ) {
        items(contents, key = { it.id }) { content ->
            Image(
                painter = painterResource(id = content.image),
                contentDescription = "Top Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
            )
        }
    }
}

@Composable
private fun Ranking20(contents: ImmutableList<HomeContents>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        itemsIndexed(contents, key = { index, content -> content.id }) { index, content ->
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
            ) {
                Text(
                    text = "${index + 1}",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic,
                    color = TvingTheme.colors.BasicWhite,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = (-30.dp))
                )

                Image(
                    painter = painterResource(id = content.image),
                    contentDescription = "Top 20",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(120.dp)
                        .clip(RoundedCornerShape(5.dp))
                )
            }
        }
    }
}

@Composable
private fun OnBoarding(contents: ImmutableList<HomeContents>) {
    HomeBasicSection(
        sectionTitle = "지금 방영 중인 콘텐츠",
        contents = contents,
        contentPaddingValues = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    )
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        paddingValues = PaddingValues(0.dp)
    )
}