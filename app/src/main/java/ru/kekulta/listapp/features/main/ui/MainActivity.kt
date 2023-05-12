package ru.kekulta.listapp.features.main.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.serialization.json.Json
import ru.kekulta.listapp.features.details.ui.Details
import ru.kekulta.listapp.features.list.ui.ListScreen
import ru.kekulta.listapp.shared.models.ArtItem
import ru.kekulta.listapp.ui.theme.ListAppTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.kekulta.listapp.shared.models.ArtParametersType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "list"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("list") {
            ListScreen { id ->
                val searchArgument = Uri.encode(Json.encodeToString(id))

                navController.navigate("details/${searchArgument}")
            }
        }
        composable("details/{ArtItem}", arguments = listOf(
            navArgument(name = "ArtItem") {
                type = ArtParametersType
            }
        )) { navBackStackEntry ->
            val item = navBackStackEntry.arguments?.getParcelable<ArtItem>("ArtItem")
            Details(item)
        }
    }
}
