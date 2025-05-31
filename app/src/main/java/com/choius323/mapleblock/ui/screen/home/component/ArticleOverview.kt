package com.choius323.mapleblock.ui.screen.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.component.MBCard
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.util.DateFormat
import org.threeten.bp.OffsetDateTime

@Composable
fun ArticleOverview(
    title: String,
    date: String,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
) {
    MBCard(modifier = modifier) {
        Column(modifier = modifier) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = title,
                modifier = Modifier
                    .size(width = 360.dp, height = 160.dp)
                    .aspectRatio(9f / 4f, matchHeightConstraintsFirst = true)
                ,
                error = painterResource(R.mipmap.ic_launcher_foreground)
            )
            MBText(title)
            MBText(OffsetDateTime.parse(date).format(DateFormat.DATE_FORMATTER))
        }
    }
}

@Preview
@Composable
private fun ArticleOverviewPreview() {
    MBTheme {
        ArticleOverview(
            "[공지] 넥슨, 폴리곤과 블록체인 게임 생태계 '메이플스토리 유니버스'로 협업",
            "2025-05-05T00:00:00+01:00",
            imageUrl = "https://example.com/image.svg"
        )
    }
}