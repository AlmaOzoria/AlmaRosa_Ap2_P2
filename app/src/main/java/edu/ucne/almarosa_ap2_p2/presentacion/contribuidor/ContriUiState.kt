package edu.ucne.almarosa_ap2_p2.presentacion.contribuidor

import edu.ucne.almarosa_ap2_p2.remote.dto.ContribuidorDto

data class ContriUiState(

    val contributors: List<ContribuidorDto> = emptyList(),
    val isLoadingContributors: Boolean = false,
    val contributorsErrorMessage: String? = null,

    )
