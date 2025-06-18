package edu.ucne.almarosa_ap2_p2.presentacion.remote

import edu.ucne.almarosa_ap2_p2.presentacion.remote.dto.RepositoryDto
import javax.inject.Inject

class DataSource @Inject constructor(
    private val api: GitHubApi
){
    suspend fun listRepos(username:String): List<RepositoryDto> = api.listRepos(username)
}