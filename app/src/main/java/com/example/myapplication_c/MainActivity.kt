package com.example.myapplication_c
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.myapplication_c.ui.theme.MyTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
                    val database = database(context = LocalContext.current)
                    Home(Homeview(repo = Repo(database)),nav)
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
                composable("cart"){
                    val database = database(context = LocalContext.current)
                   cart(viewmodel = Homeview(repo = Repo(database)))
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
fun aftercard(id: Int) {
    Text(text = "$id")
}


@Preview
@Composable
fun Preview () {
}
@Preview
@Composable
fun Preview2 () {
    val nav_ = rememberNavController()
    MyTheme {


    }

}




