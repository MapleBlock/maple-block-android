package com.choius323.mapleblock.data.community

import com.choius323.mapleblock.ui.model.WritePost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface PostRepository {
    suspend fun writePost(post: WritePost): Flow<Result<Unit>>
}

class PostRepositoryImpl(
    private val postDataSource: PostDataSourceRemote,
    private val ioDispatcher: CoroutineDispatcher,
) : PostRepository {
    override suspend fun writePost(post: WritePost): Flow<Result<Unit>> {
        return postDataSource.writePost(post).flowOn(ioDispatcher)
    }
}