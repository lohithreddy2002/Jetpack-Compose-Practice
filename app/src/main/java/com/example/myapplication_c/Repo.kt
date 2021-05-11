package com.example.myapplication_c

val items = listOf<allitems>(
    allitems(1,"biryani1",100)
,    allitems(2,"biryani2",100)
,    allitems(3,"biryani3",100)
,    allitems(4,"biryani4",100)
,    allitems(5,"biryani5",100)
,    allitems(6,"biryani6",100)
,    allitems(7,"biryani7",100)

)

class Repo:appInter {
    override suspend fun getallitems(): List<allitems> {
        return  items
    }

}