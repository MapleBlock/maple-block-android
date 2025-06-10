package com.choius323.mapleblock.data.community

import com.choius323.mapleblock.ui.model.WritePost

interface PostRepository {
    suspend fun writePost(post: WritePost): Result<Unit>
}

class PostRepositoryImpl(
    private val postDataSource: PostDataSourceRemote,
) : PostRepository {
    override suspend fun writePost(post: WritePost): Result<Unit> {
        return postDataSource.writePost(post)
    }
}