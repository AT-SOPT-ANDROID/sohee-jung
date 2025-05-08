package org.sopt.at.presentation.home.tabbar

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import kotlinx.collections.immutable.ImmutableList
import org.sopt.at.presentation.home.HomeContents
import org.sopt.at.presentation.home.component.HomeBasicSection
import org.sopt.at.ui.theme.TvingTheme
import kotlin.math.absoluteValue

@Composable
fun DramaScreen(
    dramaViewModel: DramaViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(TvingTheme.colors.BasicBlack)
    ) {
        item {
            TopBanner(
                dramaViewModel.contents,
                modifier = Modifier
                    .height(450.dp)
                    .padding(20.dp)
            )
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

            Ranking20(dramaViewModel.contents)
        }

        item {
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            OnBoarding(dramaViewModel.contents)
        }
    }
}

@Composable
private fun TopBanner(
    contents: ImmutableList<HomeContents>,
    modifier: Modifier
) {
    val pagerState = rememberPagerState(pageCount = { contents.size })

    HorizontalPager(state = pagerState) { page ->
        Card(
            modifier
                .fillMaxWidth()
                .graphicsLayer {
                    val pageOffset = (
                            pagerState.currentPage - page + pagerState.currentPageOffsetFraction
                            ).absoluteValue
                    alpha = lerp(0.7f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                }
        ) {
            Image(
                painter = painterResource(contents[page].image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
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
private fun DramaScreenPreview() {
    DramaScreen(
        dramaViewModel = DramaViewModel()
    )
}