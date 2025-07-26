package com.choius323.mapleblock.ui.model

import org.threeten.bp.LocalDateTime

data class CommunityListItem(
    val articleId: Long,
    val articleType: String,
    val title: String,
    val content: String,
    val date: LocalDateTime,
    val commentsCount: Int,
    val likesCount: Int,
    val imageUrl: String,
    val profileName: String,
    val profileImageUrl: String,
) {
    companion object {
        val sample1 = CommunityListItem(
            articleId = 1,
            articleType = "자유",
            title = "렙 250 찍었는데 사람들이랑 파티는 언제쯤 가능함요?",
            content = "스토리밀고 계속 보스 혼자서 잡는데 남들이랑 파티해서 보스 잡는건 언제쯤 하나요 팔라딘 키우는디 팔라딘 시너지 좋아서 데려간다...",
            date = LocalDateTime.of(2025, 12, 1, 14, 5),
            commentsCount = 1234,
            likesCount = 1234,
            imageUrl = "",
            profileName = "메이플지박령",
            profileImageUrl = "",
        )
        val sample2 = CommunityListItem(
            articleId = 2,
            articleType = "자유",
            title = "렙 250 찍었는데 사람들이랑 파티는 언제쯤 가능함요?",
            content = "스토리밀고 계속 보스 혼자서 잡는데 남들이랑 파티해서 보스 잡는건 언제쯤 하나요 팔라딘 키우는디 팔라딘 시너지 좋아서 데려간다...스토리밀고 계속 보스 혼자서 잡는데 남들이랑 파티해서 보스 잡는건 언제쯤 하나요 팔라딘 키우는디 팔라딘 시너지 좋아서 데려간다...",
            date = LocalDateTime.of(2025, 6, 24, 14, 5),
            commentsCount = 1234,
            likesCount = 1234,
            imageUrl = "",
            profileName = "메이플지박령",
            profileImageUrl = "",
        )
        val sample3 = CommunityListItem(
            articleId = 3,
            articleType = "자유",
            title = "렙 250 찍었는데 사람들이랑 파티는 언제쯤 가능함요?",
            content = "스토리밀고 계속 보스 혼자서 잡는데 남들이랑 파티해서 보스 잡는건 언제쯤 하나요 팔라딘 키우는디 팔라딘 시너지 좋아서 데려간다...",
            date = LocalDateTime.of(2025, 6, 24, 14, 5),
            commentsCount = 1,
            likesCount = 1,
            imageUrl = "",
            profileName = "메이플지박령",
            profileImageUrl = "",
        )
    }
}