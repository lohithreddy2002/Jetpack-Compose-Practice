package com.example.myapplication_c

import androidx.lifecycle.LiveData

val items = listOf<allitems>(
    allitems(1,"biryani1",100)
,    allitems(2,"biryani2",100)
,    allitems(3,"biryani3",100)
,    allitems(4,"biryani4",100)
,    allitems(5,"biryani5",100)
,    allitems(6,"biryani6",100)
,    allitems(7,"biryani7",100)

)

class Repo(val a:database):appInter,dataInter {
    override suspend fun getallitems(): List<allitems> {
        return  items
    }

    override fun getaddeditems(): LiveData<List<allitems>> {
        val x = a.getdao().getaddeditems()
        return x
    }

    override suspend fun additem(item: allitems) {
        a.getdao().additem(item)
    }

}