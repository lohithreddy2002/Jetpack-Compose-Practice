package com.example.myapplication_c

import android.view.animation.Animation
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.myapplication_c.components.InputFeilds
import com.example.myapplication_c.ui.theme.MyTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(title:String,desc:String,navController: NavController){

    val scaffold = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
    scaffoldState = scaffold,
    drawerContent = {
            DrawerContent(navController)
    },
    drawerShape = AbsoluteRoundedCornerShape(topLeft = 0.dp,topRight = 30.dp,bottomRight = 30.dp,bottomLeft = 0.dp),
    drawerElevation = 30.dp,
    topBar = {
            Appbar(scope, scaffold,navController)
    },
    content = {
        Column(Modifier.padding(16.dp),verticalArrangement = Arrangement.spacedBy(30.dp)) {
                card(title,desc,navController)
    }
        Column(verticalArrangement = Arrangement.Bottom,modifier = Modifier.fillMaxSize()) {
            var text by rememberSaveable{mutableStateOf("")}
            InputFeilds().FilledInputField(labeltext = "enter email",text = text,onchange = {text = it})
            Button(onClick = {

                navController.navigate("movie/$text")}) {
                Text("submit")
            }
        }
    },


)
}





@Composable
fun DrawerContent(navController: NavController) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .border(1.dp, Color.White)
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.p),
            contentDescription = "profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(20.dp)
                .clip(
                    shape = CircleShape
                )
                .width(150.dp)
                .height(150.dp)
        )
        Spacer(Modifier.height(20.dp))
        Text("profile", fontSize = 30.sp,modifier = Modifier
            .border(3.dp, Color.White)
            .padding(20.dp)
            .clickable { })
        Text(text = "settings", fontSize = 30.sp, modifier = Modifier
            .border(3.dp, Color.White)
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { })
        Text(
            "logout", fontSize = 30.sp, color = Color.White, modifier = Modifier
                .border(3.dp, Color.White)

                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}

@Composable
fun Appbar(
    scope: CoroutineScope,
    scaffold: ScaffoldState,
    navController: NavController
) {
    TopAppBar(title = { Text("Welcome") },
        navigationIcon = {
            IconButton(onClick = { scope.launch { scaffold.drawerState.open() } }) {
                Icon(Icons.Filled.Menu, "menu")

            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, "add")
            }
        })
}

@Composable
fun BottomNavigation(
    scope: CoroutineScope,
    scaffold: ScaffoldState,
    navController: NavController
) {
    Text("hai" , fontSize = 30.sp)
    BottomNavigation{
        IconButton(onClick = { navController.navigate(route = "greet") }) {
            Icon(Icons.Filled.Face, "face")

        }
    }
}


@Composable
fun card(title:String,descripton:String,navController: NavController){
    Card(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .clickable { navController.navigate("greet") },backgroundColor = Color.LightGray,elevation = 30.dp,shape = RoundedCornerShape(20.dp)){
        Image(painter = painterResource(id =R.drawable.pp), contentDescription = "image",contentScale = ContentScale.Crop)
        Column(Modifier.padding(20.dp),verticalArrangement = Arrangement.Bottom) {
            Text(text = title,fontSize = 20.sp)
            Text(text = descripton,fontSize = 16.sp)
        }

}}



//@Preview
//@Composable
//fun Preview () {
//    val nav_ = rememberNavController()
//    MyTheme(darkTheme = true) {
//        HomeScreen("Movie name","Raitng",nav_)
//
//    }
//
//}
//@Preview
//@Composable
//fun Preview2 () {
//    val nav_ = rememberNavController()
//    MyTheme() {
//        HomeScreen("Movie name","Raitng",nav_)
//
//    }
//
//}