package com.choius323.mapleblock.ui.screen.communityarticle

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.component.MBCommentTextField
import com.choius323.mapleblock.ui.component.MBHorizonDivider
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.icon.Back
import com.choius323.mapleblock.ui.icon.Bookmark
import com.choius323.mapleblock.ui.icon.Comment
import com.choius323.mapleblock.ui.icon.Edit
import com.choius323.mapleblock.ui.icon.Heart
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.model.ArticleComment
import com.choius323.mapleblock.ui.model.CommunityArticle
import com.choius323.mapleblock.ui.screen.onboarding.PagerIndicator
import com.choius323.mapleblock.ui.theme.MBColor
import com.choius323.mapleblock.ui.theme.MBColor.Gray200
import com.choius323.mapleblock.ui.theme.MBColor.Gray400
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo.Body1
import com.choius323.mapleblock.ui.theme.MBTypo.Body2
import com.choius323.mapleblock.ui.theme.MBTypo.Caption
import com.choius323.mapleblock.ui.theme.MBTypo.Subtitle2
import com.choius323.mapleblock.util.toNumString
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CommunityArticleScreen(
    modifier: Modifier = Modifier,
    viewImagePage: (Int, List<String>) -> Unit,
    viewModel: CommunityArticleViewModel = koinViewModel(),
    goBack: () -> Unit,
) {
    val uiState by viewModel.collectAsState()
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CommunityArticleSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }

            is CommunityArticleSideEffect.ViewImagePage -> {
                viewImagePage(sideEffect.index, sideEffect.imageList)
            }
        }
    }

    ProvideAppBar(
        navigationIcon = {
            Icon(
                imageVector = MBIcons.Pixel.Back,
                contentDescription = "뒤로가기",
                modifier = Modifier.clickable(onClick = goBack)
            )
        },
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = MBIcons.Pixel.Edit,
                    contentDescription = "수정하기",
                    modifier = Modifier.clickable(onClick = {
                        viewModel.onEvent(
                            CommunityArticleUiEvent.OnEditArticle
                        )
                    })
                )
                Icon(
                    imageVector = MBIcons.Pixel.Bookmark,
                    contentDescription = "북마크",
                    tint = if (uiState.isBookmarked) MBColor.Primary500 else MBColor.Gray300,
                    modifier = Modifier.clickable(onClick = {
                        viewModel.onEvent(
                            CommunityArticleUiEvent.OnBookmarkClick
                        )
                    })
                )
            }
        }
    )

    CommunityArticleContent(
        modifier = modifier.fillMaxSize(),
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )
}

@Composable
fun CommunityArticleContent(
    modifier: Modifier = Modifier,
    uiState: CommunityArticleUiState,
    onEvent: (CommunityArticleUiEvent) -> Unit,
) {
    Column(modifier) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                Column(Modifier.fillMaxWidth()) {
                    if (uiState.article != null) {
                        Spacer(Modifier.height(10.dp))
                        ArticleAuthorProfile(
                            article = uiState.article,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        PostBody(article = uiState.article, onEvent = onEvent)
                    }
                    MBHorizonDivider(thickness = 8.dp, color = Gray200)
                }
            }
            communityArticleCommentsItems(
                commentList = uiState.comments,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        MBHorizonDivider()
        MBCommentTextField(
            value = uiState.newComment,
            onValueChange = { onEvent(CommunityArticleUiEvent.OnCommentChange(it)) },
            onSend = { onEvent(CommunityArticleUiEvent.OnSubmitComment) },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}

@Composable
private fun ArticleAuthorProfile(
    modifier: Modifier = Modifier,
    article: CommunityArticle,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = R.drawable.ic_title,
            contentDescription = "Author profile",
            modifier = Modifier
                .size(48.dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            MBText(text = article.author, style = Body1)
            Spacer(Modifier.height(4.dp))
            MBText(text = article.timestamp, style = Caption, color = Gray400)
        }
    }
}

@Composable
private fun PostBody(
    article: CommunityArticle,
    modifier: Modifier = Modifier,
    onEvent: (CommunityArticleUiEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        MBText(text = "자유", style = Body2, color = Gray400)
        Spacer(modifier = Modifier.height(2.dp))
        MBText(text = article.title, style = Subtitle2)
        Spacer(modifier = Modifier.height(2.dp))
        MBText(text = article.content, style = Body2)
        if (article.images.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            ImageSection(
                imageUrlList = article.images,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                onClickImage = {
                    onEvent(CommunityArticleUiEvent.OnClickImage(it))
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        CommentsAndLikesCountRow(
            article.commentCount,
            article.likeCount,
        )
    }
}

@Composable
private fun ImageSection(
    imageUrlList: List<String>,
    modifier: Modifier = Modifier,
    onClickImage: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { imageUrlList.size })

    Box(modifier = modifier) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            AsyncImage(
                model = imageUrlList[page],
                contentDescription = "Post Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = { onClickImage(page) }),
                error = painterResource(R.drawable.img_checker),
                contentScale = ContentScale.FillWidth
            )
        }
        PagerIndicator(
            imageUrlList.size,
            pagerState.currentPage,
            modifier = Modifier
                .padding(end = 14.dp, bottom = 14.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
private fun CommentsAndLikesCountRow(
    commentCount: Int,
    likeCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(MBIcons.Pixel.Comment, "댓글 수")
        MBText(text = commentCount.toNumString(), style = Caption)
        Spacer(modifier = Modifier.width(16.dp))
        Icon(MBIcons.Pixel.Heart, "좋아요 수")
        MBText(text = likeCount.toNumString(), style = Caption)
    }
}

@Preview(showBackground = true)
@Composable
private fun CommunityArticleContentPreview() {
    val post = CommunityArticle(
        id = 1,
        title = "오늘 큐브 돌리다가...",
        content = "레전드리 등급 가서 너무 기분 좋네요 ㅎㅎㅎ 다들 득템하세요!".repeat(5),
        author = "메이플고인물",
        viewCount = 123,
        commentCount = 3,
        likeCount = 10,
        timestamp = "2024.07.25",
        images = listOf("", "")
    )
    val comments = listOf(
        ArticleComment.sample1,
        ArticleComment.sample2,
    )
    val uiState = CommunityArticleUiState(
        article = post,
        comments = comments,
        isLoading = false,
    )
    MBTheme {
        Surface {
            CommunityArticleContent(uiState = uiState, onEvent = {})
        }
    }
}