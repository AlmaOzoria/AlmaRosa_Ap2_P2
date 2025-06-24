package edu.ucne.almarosa_ap2_p2.presentacion.remote

import edu.ucne.almarosa_ap2_p2.presentacion.remote.dto.RepositoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/enelramon/repos")
    suspend fun listRepos(): Response<List<RepositoryDto>>
}