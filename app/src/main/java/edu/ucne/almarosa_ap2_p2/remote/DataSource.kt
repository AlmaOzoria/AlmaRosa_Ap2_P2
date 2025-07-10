package edu.ucne.almarosa_ap2_p2.remote

import edu.ucne.almarosa_ap2_p2.remote.dto.ContribuidorDto
import edu.ucne.almarosa_ap2_p2.remote.dto.RepositoryDto
import javax.inject.Inject

class DataSource @Inject constructor(
    private val api: GitHubApi
){
    suspend fun listRepos(): List<RepositoryDto> {
        val response = api.listRepos()
        if(response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error: ${response.code()} ${response.message()}")
        }
    }

    suspend fun listContributors(owner: String, repo: String): List<ContribuidorDto> {
        val response = api.listContributors(owner, repo)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error al obtener contribuidores: ${response.code()}")
        }
    }
}
