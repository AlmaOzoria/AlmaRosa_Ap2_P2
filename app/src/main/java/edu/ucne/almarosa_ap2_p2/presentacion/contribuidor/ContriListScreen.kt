package edu.ucne.almarosa_ap2_p2.presentacion.contribuidor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.ucne.almarosa_ap2_p2.remote.dto.ContribuidorDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContriListScreen(
    repoName: String,
    contributors: List<ContribuidorDto>,
    isLoading: Boolean,
    errorMessage: String?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contribuidores de $repoName") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)) {

            when {
                isLoading -> {
                    Text("Cargando...")
                }
                errorMessage != null -> {
                    Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
                }
                contributors.isEmpty() -> {
                    Text("No hay contribuidores.")
                }
                else -> {
                    LazyColumn {
                        items(contributors) { contributor ->
                            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                                Text(text = "Usuario: ${contributor.login}")
                                Text(text = "Contribuciones: ${contributor.contributions}")
                                Divider(modifier = Modifier.padding(top = 4.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
