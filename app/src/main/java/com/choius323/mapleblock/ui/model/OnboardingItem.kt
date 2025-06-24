package com.choius323.mapleblock.ui.model

data class OnboardingItem(
    val imageName: ImageName,
    val title: String,
    val description: String = "",
) {
    enum class ImageName {
        Notices,
        Challenge,
        Friends
    }
}