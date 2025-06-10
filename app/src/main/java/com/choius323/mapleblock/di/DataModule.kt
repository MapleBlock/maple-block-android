package com.choius323.mapleblock.di

import android.util.Log
import com.choius323.mapleblock.data.community.PostDataSourceRemote
import com.choius323.mapleblock.data.community.PostDataSourceRemoteImpl
import com.choius323.mapleblock.data.community.PostRepository
import com.choius323.mapleblock.data.community.PostRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val IoDispatcher = "IoDispatcher"

object PrettyLogger : io.ktor.client.plugins.logging.Logger {
    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            Log.d("KtorLogger", prettyJson(message)) // Pretty JSON
        } else {
            Log.d("KtorLogger", message) // Regular log
        }
    }
}

// Pretty Print JSON Function
fun prettyJson(json: String): String {
    return runCatching {
        when {
            json.startsWith("{") -> JSONObject(json).toString(4) // Format JSON Object
            json.startsWith("[") -> JSONArray(json).toString(4) // Format JSON Array
            else -> json
        }
    }.getOrDefault(
        json // Return as is if formatting fails
    )
}

object KtorClient {
    val client = HttpClient(OkHttp) {
        install(Logging) {
            logger = PrettyLogger
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true // JSON 로그 보기 좋게 출력 (개발 시 유용)
                isLenient = true // 약간의 JSON 문법 오류 허용
                ignoreUnknownKeys = true // 데이터 클래스에 정의되지 않은 키 무시
            })
        }
    }
}

val dataModule = module {
    single<CoroutineDispatcher>(named(IoDispatcher)) { Dispatchers.IO }
    single<HttpClient> { KtorClient.client }
    single<PostRepository> { PostRepositoryImpl(get()) }
    single<PostDataSourceRemote> { PostDataSourceRemoteImpl(get(named(IoDispatcher))) }
}