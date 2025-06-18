package edu.ucne.almarosa_ap2_p2.presentacion.remote

import edu.ucne.almarosa_ap2_p2.presentacion.remote.dto.RepositoryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String): List<RepositoryDto>
}