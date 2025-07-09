package edu.ucne.almarosa_ap2_p2.remote

import edu.ucne.almarosa_ap2_p2.remote.dto.RepositoryDto
import retrofit2.Response
import retrofit2.http.GET

interface GitHubApi {
    @GET("users/enelramon/repos")
    suspend fun listRepos(): Response<List<RepositoryDto>>
}