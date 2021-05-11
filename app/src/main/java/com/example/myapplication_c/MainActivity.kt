package com.example.myapplication_c
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.myapplication_c.ui.theme.MyTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

lateinit var firebase:Firebase
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        firebase = Firebase
        var start: String
        super.onCreate(savedInstanceState)
        if (firebase.auth.currentUser != null) {
            start = "aterlogin"
        } else {
            start = "home"
        }

        setContent {
            val nav = rememberNavController()

            NavHost(navController = nav, startDestination = start) {
                composable("home") {
                    Welcome(nav)
                }
                composable("login") {
                    Login(nav)
                }
                composable("aterlogin") {
                    Home(Homeview(repo = Repo()),nav)
                }
                composable("signup") {
                    Signup(nav)
                }
                composable("item/{id}", listOf(navArgument("id") { type = NavType.IntType })) {
                    it.arguments?.getInt("id").let {
                        if(it!=null){
                        aftercard(id = it)
                    }}


                }

            }
        }
    }
}









fun login(context: Context,navController: NavController,email:String,password:String){
        if(email == ""  || password == ""){
            Log.d("hi",email)
            Toast.makeText(context,"enter valid username and password",Toast.LENGTH_SHORT).show()
        }

        else{
            firebase.auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    navController.navigate("aterlogin")
                }
                else{
                    Toast.makeText(context, "not success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }










fun signup(context: Context,navController: NavController,email: String,password: String){
    if(email.isEmpty() || password.isEmpty()){
        firebase.auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                navController.navigate("login")
            }
            else if(it.isCanceled){
                Toast.makeText(context,"$it.exception",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "un success", Toast.LENGTH_SHORT).show()
            }
        }

    }


}




@Composable
fun Home(viewmodel:Homeview,navController: NavController){
    val scaffold = rememberScaffoldState()
    val coroutine = rememberCoroutineScope()
    val context= LocalContext.current
   val items by viewmodel.items.observeAsState(listOf())

    Scaffold(

        topBar = { TopAppBar(title = { Text(text = "Welcome",fontSize = 30.sp) } ,actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Person,contentDescription = "profile",modifier = Modifier.size(30.dp))
            }
        }
        )
                 },

        bottomBar = {
            BottomAppBar(
            content = {
                Row(horizontalArrangement = Arrangement.Center,modifier =Modifier.fillMaxWidth()){
                IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "settings",
                modifier = Modifier.size(30.dp)
            )
            }

            }}
        )
        },
        content = {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,contentPadding = PaddingValues(bottom = 40.dp)) {
                items(items){data->
itemcard(data,navController,scaffold,coroutine,context)
                }


            }
                  },


        )
}

@Composable
fun itemcard(item:allitems,navController: NavController,scaffoldState: ScaffoldState,coroutine:CoroutineScope,context: Context){
    //Surface( modifier = Modifier.fillMaxSize()) {
    Surface(elevation = 10.dp){
        Card(
            content = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(text = item.item_name)
                    }
                    Box(
                        modifier = Modifier
                            .padding(20.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                    ) {
                        Image(
                            painterResource(id = R.drawable.p),
                            contentDescription = "image",
                            modifier = Modifier.size(130.dp)
                        )

                        OutlinedButton(
                            onClick = {
                                coroutine.launch {
                                    Toast.makeText(
                                        context,
                                        item.item_name,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }, modifier = Modifier
                                .absoluteOffset(x = 66.dp, y = 90.dp)
                                .size(width = 60.dp, height = 30.dp)
                                .background(
                                    Color.Transparent
                                )
                                .clip(RoundedCornerShape(14.dp))
                        ) {
                            Text(text = "Add", fontSize = 10.sp)
                        }

                    }
                }
            },
            modifier = Modifier
                .padding(20.dp)
                .border(
                    1.5.dp, shape = RoundedCornerShape(10.dp), brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Red, Color.White, Color.Yellow
                        )
                    )
                )
                .clickable(onClick = { navController.navigate("item/${item.id}") })
                .fillMaxWidth(),
            elevation = 100.dp,
            shape = RoundedCornerShape(8.dp),


            )
    }
}



@Composable
fun aftercard(id: Int) {
    Text(text = "$id")
}




@Composable
fun itemcard1()
{
    Surface(elevation = 10.dp){
        Card(
            content = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(text = "1")
                    }
                    Box(modifier = Modifier
                        .padding(20.dp)
                        .clip(shape = RoundedCornerShape(20.dp))) {
                        Image(
                            painterResource(id = R.drawable.p),
                            contentDescription = "image",
                            modifier = Modifier.size(130.dp)
                        )

                        OutlinedButton(onClick = { /*TODO*/ },modifier = Modifier
                            .absoluteOffset(x = 66.dp, y = 90.dp)
                            .size(width = 60.dp, height = 30.dp)
                            .background(
                                Color.Transparent
                            )
                            .clip(RoundedCornerShape(14.dp))) {
                            Text(text = "Add",fontSize = 10.sp)
                        }
                    }
                    


                }
            },
            modifier = Modifier
                .padding(20.dp)
                .border(
                    1.5.dp, shape = RoundedCornerShape(10.dp), brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Red, Color.White, Color.Yellow
                        )
                    )
                )
                .clickable(onClick = { /*navController.navigate("item/${item.id}")*/ })
                .fillMaxWidth(),
            elevation = 100.dp,
            shape = RoundedCornerShape(8.dp),


            )
    }
}


@Preview
@Composable
fun Preview () {
    val nav_ = rememberNavController()
    MyTheme(darkTheme = true) {

itemcard1()
    }

}
@Preview
@Composable
fun Preview2 () {
    val nav_ = rememberNavController()
    MyTheme {
        itemcard1()

    }

}




