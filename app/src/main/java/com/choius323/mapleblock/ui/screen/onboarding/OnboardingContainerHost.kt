package com.choius323.mapleblock.ui.screen.onboarding

import com.choius323.mapleblock.ui.model.OnboardingItem

data class OnboardingUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val onboardingItems: List<OnboardingItem> = emptyList(),
)

sealed interface OnboardingUiEvent {
    data object OnClickLogin : OnboardingUiEvent
}

sealed interface OnboardingSideEffect {
    data object GoLogin : OnboardingSideEffect
}