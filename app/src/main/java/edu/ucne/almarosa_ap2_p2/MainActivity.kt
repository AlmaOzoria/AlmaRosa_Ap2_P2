package edu.ucne.almarosa_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import edu.ucne.almarosa_ap2_p2.presentacion.apiejemplo.ApiViewModel
import edu.ucne.almarosa_ap2_p2.presentacion.navigation.ApiNavHost
import edu.ucne.almarosa_ap2_p2.ui.theme.AlmaRosa_Ap2_P2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlmaRosa_Ap2_P2Theme {
                val navController = rememberNavController()
                val ApiViewModel: ApiViewModel = hiltViewModel()


                ApiNavHost(
                    navHostController = navController,
                    apiViewModel = apiViewModel,

                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlmaRosa_Ap2_P2Theme {
        Greeting("Android")
    }
}