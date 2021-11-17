package com.sergiorivera.demo04network.network

import com.sergiorivera.demo04network.model.Repository
import retrofit2.Call
import retrofit2.http.GET

interface GitHubService {
    @GET("repositories")
    fun getRepositories(): Call<List<Repository>>
}