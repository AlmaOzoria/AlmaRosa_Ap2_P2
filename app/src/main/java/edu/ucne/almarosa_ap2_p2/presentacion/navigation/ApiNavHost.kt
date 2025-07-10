package edu.ucne.almarosa_ap2_p2.presentacion.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.almarosa_ap2_p2.presentacion.apiejemplo.ApiListScreen
import edu.ucne.almarosa_ap2_p2.presentacion.apiejemplo.ApiViewModel
import edu.ucne.almarosa_ap2_p2.presentacion.contribuidor.ContriListScreen
import edu.ucne.almarosa_ap2_p2.presentacion.contribuidor.ContriViewModel


@Composable
fun ApiNavHost(
    navHostController: NavHostController,
    apiViewModel: ApiViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = "ApiList"
    ) {
        composable("ApiList") {
            val uiState by apiViewModel.uiState.collectAsState()

            ApiListScreen(
                apiUiState = uiState,
                onCreate = {
                    navHostController.navigate("Api/null")
                },
                onEdit = {},
                onDelete = {},
                onClick = { repo ->
                    navHostController.navigate("contributors/${repo.owner.login}/${repo.name}")
                }
            )
        }


        composable("contributors/{owner}/{repo}") { backStackEntry ->
            val owner = backStackEntry.arguments?.getString("owner") ?: ""
            val repo = backStackEntry.arguments?.getString("repo") ?: ""

            val contriViewModel: ContriViewModel = hiltViewModel()
            val uiState by contriViewModel.uiState.collectAsState()

            LaunchedEffect(owner, repo) {
                contriViewModel.getContributors(owner, repo)
            }

            ContriListScreen(
                repoName = repo,
                contributors = uiState.contributors,
                isLoading = uiState.isLoadingContributors,
                errorMessage = uiState.contributorsErrorMessage,
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
