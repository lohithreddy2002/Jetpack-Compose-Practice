package com.example.myapplication_c
import androidx.compose.foundation.lazy.items
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.myapplication_c.ui.theme.MyTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

lateinit var firebase:Firebase
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        firebase = Firebase
        var start:String
        super.onCreate(savedInstanceState)
        if(firebase.auth.currentUser != null){
            start = "aterlogin"
        }
        else{
            start = "home"
        }

        setContent {
            val nav = rememberNavController()

            NavHost(navController = nav, startDestination = start ){
                composable("home"){
                    Welcome(nav)
                }
                composable("login"){
                   Login(nav)
                }
                composable("aterlogin"){
                    Home()
                }
                composable("signup"){
                    Signup(nav)
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

val itemcardlist = listOf<String>("1","2","3","4","5")


@Composable
fun Home(){
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
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                items(itemcardlist){
itemcard()
                }


            }
                  },


        )
}

@Composable
fun itemcard(){
    //Surface( modifier = Modifier.fillMaxSize()) {
    Surface(elevation = 10.dp){
        Card(content = {
            Row(horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
                Column() {
                    Text(text = "Hai")
                }
                Image(painterResource(id = R.drawable.p), contentDescription = "image",modifier = Modifier.size(130.dp))



            }
        },modifier = Modifier
            .padding(20.dp)
            .border(
                1.5.dp, shape = RoundedCornerShape(10.dp), brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Red, Color.White, Color.Yellow
                    )
                )
            ).clickable {  }.fillMaxWidth(),
        elevation = 100.dp,
            shape = RoundedCornerShape(8.dp),


        )
    //}
}}





@Preview
@Composable
fun Preview () {
    val nav_ = rememberNavController()
    MyTheme(darkTheme = true) {
Home()
    }

}
@Preview
@Composable
fun Preview2 () {
    val nav_ = rememberNavController()
    MyTheme {
      Home()

    }

}




