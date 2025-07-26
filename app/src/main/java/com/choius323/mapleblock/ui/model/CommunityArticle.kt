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
)