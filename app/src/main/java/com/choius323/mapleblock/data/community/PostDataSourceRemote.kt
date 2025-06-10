package com.choius323.mapleblock.data.community

import com.choius323.mapleblock.ui.model.WritePost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay

interface PostDataSourceRemote {
    suspend fun writePost(post: WritePost): Result<Unit>
}

class PostDataSourceRemoteImpl(
    private val ioDispatcher: CoroutineDispatcher
) : PostDataSourceRemote {
    override suspend fun writePost(post: WritePost): Result<Unit> {
        delay(1000L)
        return Result.success(Unit)
    }
}