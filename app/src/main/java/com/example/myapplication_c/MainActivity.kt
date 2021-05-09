package com.example.myapplication_c

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.myapplication_c.components.InputFeilds
import com.example.myapplication_c.ui.theme.MyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val nav = rememberNavController()
            NavHost(navController = nav, startDestination = "home" ){
                composable("home"){
                Home(nav)
                }
                composable("login"){
                   Login(nav)
                }
                composable("aterlogin"){
                    next()
                }

            }
            }
        }
    }



@Composable
fun Home(navController: NavController){
    Surface{
    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",modifier = Modifier
            .clip(shape = CircleShape)
            .size(100.dp))
        Spacer(Modifier.size(30.dp))
        Text(text = "Covid Care",fontSize = 50.sp)
Spacer(Modifier.size(50.dp))
        Button(onClick = { navController.navigate("login") }, shape = RoundedCornerShape(25.dp),modifier = Modifier
            .width(230.dp)
            .height(45.dp)) {
                Text("Login")
            }
        Spacer(Modifier.size(20.dp))
        Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(25.dp),modifier = Modifier
            .width(230.dp)
            .height(45.dp)) {
            Text("Signup")
        }

    }

}}


class loginviewmodel(){
    fun login(context: Context,navController: NavController,email:String,password:String){
        if(email == ""  || password == ""){
            Log.d("hi",email)
            Toast.makeText(context,"enter valid username and password",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "click    $email + $password", Toast.LENGTH_SHORT).show()
            navController.navigate("aterlogin")


        }
    }
}




@Composable
fun Login(navController: NavController){
    val context = LocalContext.current
    var password by remember { mutableStateOf("")}
    var email by remember { mutableStateOf("")}
    Surface() {

        Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),verticalArrangement = Arrangement.Center) {
            Text(text = "Login",fontSize = 30.sp,modifier = Modifier)

        InputFeilds().OutlinedInputField(text = email,onchange = {email = it},labeltext = "Email id",keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))

        InputFeilds().OutlinedInputField(text = password,onchange = {password = it},labeltext = "Password",keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,imeAction = ImeAction.Done),visualTransformation = PasswordVisualTransformation())
            Spacer(Modifier.size(20.dp))
            Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {loginviewmodel().login(context,navController,email,password)}, shape = RoundedCornerShape(10.dp), modifier = Modifier
                        .width(100.dp)
                        .height(45.dp)
                ) {
                    Text("Login",fontSize = 20.sp)
                }
            }

        }
}
}







@Composable
fun next(){
    Text(text = "fter submit")
}






@Preview
@Composable
fun Preview () {
    val nav_ = rememberNavController()
    MyTheme(darkTheme = true) {
        Login(nav_)
    }

}
@Preview
@Composable
fun Preview2 () {
    val nav_ = rememberNavController()
    MyTheme() {
        Login(nav_)

    }

}




