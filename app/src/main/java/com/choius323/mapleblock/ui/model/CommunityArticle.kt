package com.choius323.mapleblock.ui.model

data class CommunityArticle(
    val id: Int,
    val title: String,
    val content: String,
    val author: String,
    val viewCount: Int,
    val commentCount: Int,
    val likeCount: Int,
    val timestamp: String,
    val images: List<String> = emptyList(),
    val authorImageUrl: String? = null,
) {
    companion object {
        val sample1 = CommunityArticle( // CommunityArticle 모델 사용
            id = 1,
            title = "오늘 큐브 돌리다가...",
            content = "레전드리 등급 가서 너무 기분 좋네요 ㅎㅎㅎ 다들 득템하세요!",
            author = "메이플고인물",
            viewCount = 123,
            commentCount = 3,
            likeCount = 10,
            timestamp = "2024.07.25",
            images = listOf(
                "https://apis.map.kakao.com/android_v2/decal.gif",
                "https://apis.map.kakao.com/android_v2/labelstyle_desc.png"
            )
        )
    }
}