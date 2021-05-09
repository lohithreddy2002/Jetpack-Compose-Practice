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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.myapplication_c.components.InputFeilds
import com.example.myapplication_c.ui.theme.MyTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

lateinit var firebase:Firebase
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var start:String
        super.onCreate(savedInstanceState)
        if(firebase.auth.currentUser == null){
            start = "aterlogin"
        }
        else{
            start = "home"
        }

        setContent {
            val nav = rememberNavController()

            NavHost(navController = nav, startDestination = start ){
                composable("home"){
                    Home(nav)
                }
                composable("login"){
                   Login(nav)
                }
                composable("aterlogin"){
                    next()
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
            else{
                Toast.makeText(context, "un success", Toast.LENGTH_SHORT).show()
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
        Signup(nav_)
    }

}
@Preview
@Composable
fun Preview2 () {
    val nav_ = rememberNavController()
    MyTheme() {
        Signup(nav_)

    }

}




