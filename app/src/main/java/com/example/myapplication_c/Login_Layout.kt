package com.example.myapplication_c
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication_c.components.InputFeilds

@Composable
fun Login(navController: NavController){
    val context = LocalContext.current
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
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
                    onClick = {login(context,navController,email,password)}, shape = RoundedCornerShape(10.dp), modifier = Modifier
                        .width(100.dp)
                        .height(45.dp)
                ) {
                    Text("Login",fontSize = 20.sp)
                }
            }

        }
    }
}


