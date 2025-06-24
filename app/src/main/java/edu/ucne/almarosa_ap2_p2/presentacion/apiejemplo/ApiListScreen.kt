package edu.ucne.almarosa_ap2_p2.presentacion.apiejemplo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.almarosa_ap2_p2.presentacion.remote.dto.RepositoryDto
import kotlinx.coroutines.flow.*

@Composable
fun ApiListScreen(
    apiUiState: ApiUiState,
    onCreate: () -> Unit,
    onDelete: (RepositoryDto) -> Unit = {},
    onEdit: (RepositoryDto) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var debouncedQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }
            .debounce(400)
            .collectLatest { debouncedQuery = it }
    }

    val filteredList = if (debouncedQuery.isBlank()) {
        apiUiState.Api
    } else {
        apiUiState.Api.filter {
            it.name.contains(debouncedQuery, ignoreCase = true) ||
                    it.description?.contains(debouncedQuery, ignoreCase = true) == true ||
                    it.htmlUrl.contains(debouncedQuery, ignoreCase = true)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreate,
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ) {
            Text(
                text = "Lista de Repositorios",
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202121),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar Repositorio") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                apiUiState.isLoading -> {
                    Text("Cargando...", modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                apiUiState.errorMessage != null -> {
                    Text(
                        text = apiUiState.errorMessage,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                else -> {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                        items(filteredList) { repo ->
                            ApiRow(repository = repo, onDelete = onDelete, onEdit = onEdit)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ApiRow(
    repository: RepositoryDto,
    onDelete: (RepositoryDto) -> Unit,
    onEdit: (RepositoryDto) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(14.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(22.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Nombre: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = repository.name, fontSize = 16.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Descripción: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = repository.description ?: "-", fontSize = 16.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "URL: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = repository.htmlUrl, fontSize = 16.sp)
                }
            }

        }
    }
}
