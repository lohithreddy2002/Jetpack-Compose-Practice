package com.example.myapplication_c

data class allitems (
    val id : Int,
    val item_name :String,
    val price:Int,
    )


interface appInter {
    suspend fun getallitems():List<allitems>

}