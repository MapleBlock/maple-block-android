package com.choius323.mapleblock.ui.model

data class ArticleComment(
    val id: Long,
    val author: String,
    val content: String,
    val timestamp: String,
    val isLiked: Boolean,
    val likeCount: Int,
    val authorImageUrl: String? = null,
) {
    companion object {
        val sample1 = ArticleComment(1, "초보모험가", "와 정말 축하드려요!", "1분 전", true, 123, null)
        val sample2 = ArticleComment(2, "모험가123", "축하드려요!", "1분 전", false, 0, null)
        val sample3 = ArticleComment(
            3, "주니어발록", "와 요즘 누가 팔라딘 키움; 팔라딘 하향먹은지 꽤 됐음요 걍 직첸하셈", "3분 전", true, 1589, null
        )
        val sample4 = ArticleComment(4, "헤네시스주민", "기운 받아갑니다~", "10분 전", false, 1, null)
    }
}