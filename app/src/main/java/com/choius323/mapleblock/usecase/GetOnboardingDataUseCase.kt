package com.choius323.mapleblock.usecase

import com.choius323.mapleblock.ui.model.OnboardingItem

class GetOnboardingDataUseCase(
) {
    operator fun invoke(): List<OnboardingItem> {
        return listOf(
            OnboardingItem(
                imageName = OnboardingItem.ImageName.Notices,
                title = "다양한 채널의 공지사항",
                description = "한국어 번역 제공"
            ),
            OnboardingItem(
                imageName = OnboardingItem.ImageName.Challenge,
                title = "나만의 챌린지",
                description = "목표를 설정하고 달성해보세요"
            ),
            OnboardingItem(
                imageName = OnboardingItem.ImageName.Friends,
                title = "친구와 함께",
                description = "경쟁하고 성장하는 재미"
            )
        )
    }
}
