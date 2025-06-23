package com.choius323.mapleblock.usecase

import com.choius323.mapleblock.ui.model.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetNoticeListUseCase() {
    suspend operator fun invoke(type: String): Flow<Result<List<Notice>>> {
        return flowOf(
            Result.success(
                listOf(
                    Notice(1, "Notice 1", "Notice 1 Content"),
                    Notice(2, "Notice 2", "Notice 2 Content"),
                    Notice(3, "Notice 3", "Notice 3 Content"),
                    Notice(4, "Notice 4", "Notice 4 Content"),
                    Notice(5, "Notice 5", "Notice 5 Content"),
                    Notice(6, "Notice 6", "Notice 6 Content"),
                )
            )
        )
    }
}
