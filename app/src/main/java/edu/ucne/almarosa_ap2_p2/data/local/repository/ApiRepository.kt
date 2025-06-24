package edu.ucne.almarosa_ap2_p2.data.local.repository

import edu.ucne.almarosa_ap2_p2.presentacion.remote.DataSource
import edu.ucne.almarosa_ap2_p2.presentacion.remote.Resource
import edu.ucne.almarosa_ap2_p2.presentacion.remote.dto.RepositoryDto
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
}