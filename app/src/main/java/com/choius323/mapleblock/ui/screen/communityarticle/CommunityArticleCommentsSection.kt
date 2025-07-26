package com.choius323.mapleblock.ui.screen.communityarticle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.component.MBHorizonDivider
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.icon.Heart
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.icon.filled.Heart
import com.choius323.mapleblock.ui.model.ArticleComment
import com.choius323.mapleblock.ui.theme.MBColor
import com.choius323.mapleblock.ui.theme.MBColor.Gray400
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo.Body1
import com.choius323.mapleblock.ui.theme.MBTypo.Body2
import com.choius323.mapleblock.ui.theme.MBTypo.Caption
import com.choius323.mapleblock.util.toNumString

fun LazyListScope.communityArticleCommentsItems(
    commentList: List<ArticleComment>,
    modifier: Modifier = Modifier,
) = itemsIndexed(commentList, { _, item -> item.id }) { index, comment ->
    CommentItem(comment = comment, modifier = modifier)
    if (index < commentList.lastIndex) {
        MBHorizonDivider()
    }
}

@Composable
fun CommentItem(
    comment: ArticleComment,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        AsyncImage(
            model = comment.authorImageUrl ?: R.drawable.img_checker,
            contentDescription = "Commenter Profile Picture",
            modifier = Modifier
                .size(32.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            MBText(text = comment.author, style = Body2)
            Spacer(modifier = Modifier.height(2.dp))
            MBText(text = comment.content, style = Body1)
            Spacer(modifier = Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                MBText("등록하기", style = Caption, color = Gray400, modifier = Modifier.clickable {})
                MBText(" | ", style = Caption, color = Gray400)
                MBText("수정하기", style = Caption, color = Gray400, modifier = Modifier.clickable {})
            }
        }
        Spacer(Modifier.width(32.dp))
        LikeSection(
            comment.likeCount,
            isLiked = comment.isLiked,
            onClickLike = {},
            modifier = Modifier
        )
    }
}

@Composable
private fun LikeSection(
    count: Int,
    isLiked: Boolean,
    modifier: Modifier = Modifier,
    onClickLike: () -> Unit,
) {
    val color = if (isLiked) MBColor.Primary500 else MBColor.Gray300
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
    ) {
        Icon(
            if (isLiked) MBIcons.Pixel.Filled.Heart else MBIcons.Pixel.Heart,
            contentDescription = "댓글 좋아요",
            tint = color,
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onClickLike),
        )
        MBText(
            count.toNumString(),
            textAlign = TextAlign.Center,
            color = color,
            style = Caption
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentItemPreview() {
    val list = listOf(
        ArticleComment.sample1,
        ArticleComment.sample2,
        ArticleComment.sample3,
        ArticleComment.sample4
    )
    MBTheme {
        LazyColumn {
            communityArticleCommentsItems(commentList = list)
        }
    }
}