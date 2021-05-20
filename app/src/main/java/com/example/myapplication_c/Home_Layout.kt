package com.example.myapplication_c

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
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
                    Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()){
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "settings",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(onClick = { navController.navigate("cart") }) {
                            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "cart" )

                        }

                    }
                }
            )
        },
        content = {
            val state = rememberLazyListState()

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,contentPadding = PaddingValues(bottom = 40.dp),state = state) {
                items(items){data->
                    itemcard(data,navController,scaffold,coroutine,context,viewmodel)
                }


            }
        },


        )
}



@ExperimentalAnimationApi
@Composable
fun itemcard(item:allitems, navController: NavController, scaffoldState: ScaffoldState, coroutine: CoroutineScope, context: Context, viewmodel: Homeview){
    //Surface( modifier = Modifier.fillMaxSize()) {
    var expanded by rememberSaveable { mutableStateOf(false)}
    var qunatity by rememberSaveable {
        mutableStateOf(1)
    }
        Surface(elevation = 10.dp) {
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
                                    .padding(10.dp)
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
                                            viewmodel.additem(item)
                                        }
                                        expanded = true
                                    }, modifier = Modifier
                                        .offset(x = 16.dp, y = 87.dp)
                                        .size(width = 90.dp, height = 40.dp)
                                        .background(
                                            Color.Transparent
                                        )
                                        .clip(RoundedCornerShape(14.dp))
                                ) {
                                    if(!expanded){
                                        Text("ADD",fontSize = 10.sp,color = Color.Red,modifier = Modifier.fillMaxWidth())
                                    }

                                    AnimatedVisibility(visible = expanded) {
                                        Row(modifier = Modifier.width(60.dp),horizontalArrangement = Arrangement.SpaceAround) {
                                            IconButton(onClick = { qunatity += 1

                                                item.quantity = qunatity
                                                viewmodel.update(item)
                                            },Modifier.size(25.dp)) {
                                                Icon(Icons.Filled.Add, contentDescription = "add",modifier = Modifier.size(15.dp))
                                            }
                                            Text(text = "$qunatity", fontSize = 16.sp)

                                            IconButton(onClick = {
                                                viewmodel.update(item)
                                                if(qunatity<=1){
                                                expanded = false
                                                    viewmodel.delete(item)
                                            }
                                                else{
                                                    qunatity -= 1
                                                    item.quantity = qunatity
                                                }

                                                viewmodel.update(item)

                                            },Modifier.size(25.dp)) {
                                                Icon(Icons.Filled.Add, contentDescription = "add",modifier = Modifier.size(15.dp))
                                            }
                                        }
                                    }

                                }

                            }
                        }},
                        modifier = Modifier
                            .padding(3.dp)
                            .border(
                                1.5.dp,
                                shape = RoundedCornerShape(10.dp),
                                brush = Brush.linearGradient(
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
fun cart(viewmodel:Homeview){
    val a by viewmodel.getaddeditems().observeAsState()
    if(a == null){
        Text("ai")
    }
    else{
  LazyColumn() {

      items(a!!) { data ->
          Text("${data?.item_name}")

      }

  }

          }}


@ExperimentalAnimationApi
@Preview
@Composable
fun pre(){
    var expanded by remember { mutableStateOf(false) }
    var qunatity by remember {
        mutableStateOf(1) }
   itemcard(
       item = allitems(1,"hai",1000),
       navController = rememberNavController(),
       scaffoldState = rememberScaffoldState(),
       coroutine = rememberCoroutineScope(),
       context = LocalContext.current,
       viewmodel = Homeview(repo = Repo( database(LocalContext.current)))
   )
}