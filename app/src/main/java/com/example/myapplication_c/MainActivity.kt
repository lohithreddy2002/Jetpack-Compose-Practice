package com.example.myapplication_c

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.myapplication_c.ui.theme.MyTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

        val nav = rememberNavController()
            NavHost(navController = nav, startDestination = "home") {
                composable("home"){
                    HomeScreen(title = "moviee name", desc = "rating",nav)
                }
                composable("greet"){
                    Greeting(name = "Lohith",nav)
                }
                composable("movie/{movie}",arguments = listOf(
                    navArgument(name = "moive"){
                        type = NavType.StringType
                    }
                )){back->

                    val moviename = back.arguments?.getString("movie")

                    if (moviename != null) {
                        Movie(moviename)
                    }
                }


            }
            }
        }
    }

@Composable
fun Movie(name:String){
    val sca = rememberScaffoldState()
val co = rememberCoroutineScope()
    Scaffold(
        scaffoldState = sca,
        topBar = {
            Appbar(scope = co , scaffold = sca, navController = rememberNavController())

        },
    content = {
        Text(name,fontSize = 30.sp)
        }

    )
}





@Composable
fun Greeting(name: String,navController: NavController) {
    val scaffoldstate = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(

        scaffoldState = scaffoldstate,
        drawerContent = {
                        DrawerContent(navController = navController)
        },
        topBar = {

                 Appbar(scope = scope, scaffold =scaffoldstate , navController = navController )
        },
        content = {

        }
            )
    
    }
