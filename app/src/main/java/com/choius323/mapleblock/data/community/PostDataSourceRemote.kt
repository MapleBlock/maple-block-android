package com.choius323.mapleblock.data.community

import com.choius323.mapleblock.ui.model.WritePost
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PostDataSourceRemote {
    suspend fun writePost(post: WritePost): Flow<Result<Unit>>
}

class PostDataSourceRemoteImpl(
    val client: HttpClient,
) : PostDataSourceRemote {
    override suspend fun writePost(post: WritePost): Flow<Result<Unit>> = flow {
        delay(1000L)
        emit(Result.success(Unit))
    }
}