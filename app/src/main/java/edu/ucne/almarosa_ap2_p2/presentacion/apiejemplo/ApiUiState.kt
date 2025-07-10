package edu.ucne.almarosa_ap2_p2.presentacion.apiejemplo

import edu.ucne.almarosa_ap2_p2.remote.dto.ContribuidorDto
import edu.ucne.almarosa_ap2_p2.remote.dto.RepositoryDto

data class ApiUiState (
        val name: String = "",
        val description: String? = null,
        val htmlUrl: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val Api: List<RepositoryDto> = emptyList(),
        val inputError: String? = null

)