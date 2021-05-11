package com.example.myapplication_c

val items = listOf<allitems>(
    allitems(1,"biryani1",100)
,    allitems(1,"biryani2",100)
,    allitems(1,"biryani3",100)
,    allitems(1,"biryani4",100)
,    allitems(1,"biryani5",100)
,    allitems(1,"biryani6",100)
,    allitems(1,"biryani7",100)

)

class Repo:appInter {
    override suspend fun getallitems(): List<allitems> {
        return  items
    }

}