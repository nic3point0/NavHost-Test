package dev.nic3point0.nevhosttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.nic3point0.nevhosttest.ui.theme.NevHostTestTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NevHostTestTheme {

                val navController = rememberNavController()
                NevHostTestApp(navController)

            }
        }
    }
}



@Composable
fun NevHostTestApp(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = NavigationRouts.OVERVIEW.name
    ) {

        composable(NavigationRouts.OVERVIEW.name) {

            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopBar(data = TopBarData(
                        R.string.title_overview,
                        Icons.Filled.Menu,
                        R.string.icon_desc_menu
                    )){
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                },
                drawerGesturesEnabled = true,
                drawerContent = {
                    Drawer{
                        scope.launch {
                            navController.navigate(NavigationRouts.ABOUT.name)
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            ) {

                ScreenContent("Hello World!")

            }

        }

        composable(NavigationRouts.ABOUT.name) {
            Scaffold(
                topBar = {
                    TopBar(data = TopBarData(
                        R.string.title_about,
                        Icons.Filled.ArrowBack,
                        R.string.icon_desc_back
                    )){
                        navController.navigate(NavigationRouts.OVERVIEW.name)
                    }
                }
            ) {
                ScreenContent("About Me.")
            }
        }

    }

}



data class TopBarData(
    @StringRes val title: Int,
    val iconVector: ImageVector,
    @StringRes val iconDescription: Int
)

enum class NavigationRouts {
    OVERVIEW, ABOUT
}
