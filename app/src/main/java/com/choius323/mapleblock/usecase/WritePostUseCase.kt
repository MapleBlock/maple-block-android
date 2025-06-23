package com.choius323.mapleblock.usecase

import com.choius323.mapleblock.data.community.PostRepository
import com.choius323.mapleblock.ui.model.WritePost
import kotlinx.coroutines.flow.Flow

class WritePostUseCase(
    private val postRepository: PostRepository,
) {
    suspend operator fun invoke(
        title: String,
        content: String,
    ): Flow<Result<Unit>> {
        return postRepository.writePost(WritePost(title, content))
    }
}