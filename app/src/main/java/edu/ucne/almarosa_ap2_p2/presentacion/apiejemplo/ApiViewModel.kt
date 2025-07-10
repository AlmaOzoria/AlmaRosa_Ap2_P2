package edu.ucne.almarosa_ap2_p2.presentacion.apiejemplo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.almarosa_ap2_p2.repository.ApiRepository
import edu.ucne.almarosa_ap2_p2.remote.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class ApiViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ApiUiState())
    val uiState: StateFlow<ApiUiState> get() = _uiState

    init {
        getApis()
    }

    fun getApis() {
        viewModelScope.launch {
            try {
                repository.getApi().collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                Api = result.data ?: emptyList(),
                                errorMessage = null
                            )
                        }
                        is Resource.Error -> {
                            Log.e("ApiViewModel", "Error en getApi: ${result.message}")
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("ApiViewModel", "Excepción en getApis", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }


}