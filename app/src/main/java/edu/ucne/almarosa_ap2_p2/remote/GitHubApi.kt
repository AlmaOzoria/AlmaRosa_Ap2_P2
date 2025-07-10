package edu.ucne.almarosa_ap2_p2.remote

import edu.ucne.almarosa_ap2_p2.remote.dto.ContribuidorDto
import edu.ucne.almarosa_ap2_p2.remote.dto.RepositoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/enelramon/repos")
    suspend fun listRepos(): Response<List<RepositoryDto>>

    @GET("repos/{owner}/{repo}/contributors")
    suspend fun listContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<List<ContribuidorDto>>

}
