package com.example.contribmontano.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GithubFactory {
    private const val BASE_URL = "https://api.github.com/"

    fun makeGithubService(): GithubService {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(GithubService::class.java)
    }
}