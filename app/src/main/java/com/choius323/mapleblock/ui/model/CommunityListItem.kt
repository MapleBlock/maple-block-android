package com.choius323.mapleblock.ui.model

import org.threeten.bp.LocalDateTime

data class CommunityListItem(
    val articleType: String,
    val title: String,
    val content: String,
    val date: LocalDateTime,
    val commentsCount: Int,
    val likesCount: Int,
    val imageUrl: String,
    val profileName: String,
    val profileImageUrl: String,
)