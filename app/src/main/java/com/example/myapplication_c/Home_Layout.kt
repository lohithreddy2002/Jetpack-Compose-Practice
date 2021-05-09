package com.example.myapplication_c

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate


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
            Button(onClick = {navController.navigate("signup") }, shape = RoundedCornerShape(25.dp),modifier = Modifier
                .width(230.dp)
                .height(45.dp)) {
                Text("Signup")
            }

        }

    }
}