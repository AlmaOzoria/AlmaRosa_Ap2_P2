package edu.ucne.almarosa_ap2_p2.presentacion.contribuidor

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.almarosa_ap2_p2.remote.Resource
import edu.ucne.almarosa_ap2_p2.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContriViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContriUiState())
    val uiState: StateFlow<ContriUiState> get() = _uiState

    fun getContributors(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    isLoadingContributors = true,
                    contributorsErrorMessage = null
                )

                repository.getContributors(owner, repo).collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoadingContributors = true)
                        }
                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoadingContributors = false,
                                contributors = result.data ?: emptyList(),
                                contributorsErrorMessage = null
                            )
                        }
                        is Resource.Error -> {
                            Log.e("ContriViewModel", "Error en getContributors: ${result.message}")
                            _uiState.value = _uiState.value.copy(
                                isLoadingContributors = false,
                                contributorsErrorMessage = result.message
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("ContriViewModel", "Excepción en getContributors", e)
                _uiState.value = _uiState.value.copy(
                    isLoadingContributors = false,
                    contributorsErrorMessage = e.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }
}