package com.choius323.mapleblock.ui.screen.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.model.CommunityListItem
import com.choius323.mapleblock.ui.theme.Gray30
import com.choius323.mapleblock.ui.theme.MBTheme
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CommunityListItem(
    data: CommunityListItem,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        // 1. 이미지 및 프로필 정보 섹션
        ImageSection(
            imageUrl = data.imageUrl,
            profileImageUrl = data.profileImageUrl,
            profileName = data.profileName,
            title = data.title
        )

        // 2. 텍스트 컨텐츠 섹션
        ContentSection(
            articleType = data.articleType,
            title = data.title,
            content = data.content,
            date = data.date,
            commentsCount = data.commentsCount,
            likesCount = data.likesCount
        )
    }
}

@Composable
private fun ImageSection(
    imageUrl: String,
    profileImageUrl: String,
    profileName: String,
    title: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f) // 이미지 비율
    ) {
        // 배경 이미지
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY,
                    )
                )
        )
        // 프로필 정보
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(profileImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "$profileName profile picture",
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            MBText(
                text = profileName,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ContentSection(
    articleType: String,
    title: String,
    content: String,
    date: LocalDateTime,
    commentsCount: Int,
    likesCount: Int,
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        MBText(text = articleType, color = Gray30, fontSize = 12.sp)
        MBText(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        MBText(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(6.dp))

        // 날짜, 댓글, 좋아요 정보
        Row(verticalAlignment = Alignment.CenterVertically) {
            val formatter = remember { DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm") }
            MBText(text = date.format(formatter), color = Gray30, fontSize = 12.sp)
            Spacer(Modifier.weight(1f))
            IconCount(icon = Icons.Outlined.ChatBubbleOutline, count = commentsCount)
            Spacer(Modifier.width(12.dp))
            IconCount(icon = Icons.Outlined.FavoriteBorder, count = likesCount)
        }
    }
}

@Composable
private fun IconCount(icon: ImageVector, count: Int, modifier: Modifier = Modifier) {
    val numberFormatter = remember { NumberFormat.getNumberInstance(Locale.US) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Gray30
        )
        MBText(
            text = numberFormatter.format(count),
            color = Gray30,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun CommunityListItemPreview() {
    val sampleData = CommunityListItem(
        articleType = "자유",
        title = "렙 250 찍었는데 사람들이랑 파티는 언제쯤 가능함요?",
        content = "스토리밀고 계속 보스 혼자서 잡는데 남들이랑 파티해서 보스 잡는건 언제쯤 하나요 팔라딘 키우는디 팔라딘 시너지 좋아서 데려간다...",
        date = LocalDateTime.of(2025, 6, 24, 14, 5),
        commentsCount = 1234,
        likesCount = 1234,
        imageUrl = "",
        profileName = "메이플지박령",
        profileImageUrl = "",
    )
    MBTheme {
        Surface {
            Box(modifier = Modifier.padding(16.dp)) {
                CommunityListItem(data = sampleData)
            }
        }
    }
}