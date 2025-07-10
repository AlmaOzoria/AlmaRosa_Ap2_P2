package edu.ucne.almarosa_ap2_p2.repository

import edu.ucne.almarosa_ap2_p2.remote.DataSource
import edu.ucne.almarosa_ap2_p2.remote.Resource
import edu.ucne.almarosa_ap2_p2.remote.dto.ContribuidorDto
import edu.ucne.almarosa_ap2_p2.remote.dto.RepositoryDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val dataSource: DataSource
) {
    fun getApi(): Flow<Resource<List<RepositoryDto>>> = flow {
        try {
            emit(Resource.Loading())
            val repos = dataSource.listRepos()
            emit(Resource.Success(repos))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message}"))
        }
    }

    fun getContributors(owner: String, repo: String): Flow<Resource<List<ContribuidorDto>>> = flow {
        try {
            emit(Resource.Loading())
            val contributors = dataSource.listContributors(owner, repo)
            emit(Resource.Success(contributors))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message}"))
        }
    }
}