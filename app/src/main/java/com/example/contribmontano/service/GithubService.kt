package com.example.contribmontano.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

data class Repository (
    val name: String,
    val html_url: String
)

interface GithubService {
    @GET("/users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String?): Response<List<Repository>>
}