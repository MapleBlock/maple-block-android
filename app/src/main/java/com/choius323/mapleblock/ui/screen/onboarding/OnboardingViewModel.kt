package com.choius323.mapleblock.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import com.choius323.mapleblock.usecase.GetOnboardingDataUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class OnboardingViewModel(
    private val getOnBoardingDataUseCase: GetOnboardingDataUseCase,
) : ContainerHost<OnboardingUiState, OnboardingSideEffect>, ViewModel() {

    override val container = container<OnboardingUiState, OnboardingSideEffect>(OnboardingUiState())

    init {
        loadOnboardingData()
    }

    fun onEvent(event: OnboardingUiEvent) {
        when (event) {
            OnboardingUiEvent.OnClickLogin -> onLoginClicked()
        }
    }

    private fun loadOnboardingData() = intent {
        val items = getOnBoardingDataUseCase()
        reduce {
            state.copy(onboardingItems = items)
        }
    }

    // 로그인 버튼 클릭 시 호출
    fun onLoginClicked() = intent {
        postSideEffect(OnboardingSideEffect.GoLogin)
    }
}