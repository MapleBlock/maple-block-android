package com.choius323.mapleblock.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class Notice(
    val id: Long = 0L,
    val title: String = "title",
    val content: String = "content",
)
