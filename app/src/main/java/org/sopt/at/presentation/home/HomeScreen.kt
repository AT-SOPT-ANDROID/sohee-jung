package org.sopt.at.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.component.lazylist.HomeBasicSection
import org.sopt.at.data.contents

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        item {
            TopBanner(contents)
        }

        item {
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "오늘의 티빙 TOP 20",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Ranking20(contents)

            }
        }

        item {
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            OnBoarding(contents)
        }
    }
}

@Composable
fun TopBanner(contents: List<HomeContentsEntity>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .padding(top = 20.dp)
    ) {
        items(contents) { content ->
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
fun Ranking20(
    contents: List<HomeContentsEntity>
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        itemsIndexed(contents) { index, content ->
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
                    color = Color.White,
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
fun OnBoarding(contents: List<HomeContentsEntity>) {
    HomeBasicSection(
        sectionTitle = "지금 방영 중인 콘텐츠",
        contents = contents,
        contentPaddingValues = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentDescription = "지금 방영 중인 콘텐츠"
    )
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}