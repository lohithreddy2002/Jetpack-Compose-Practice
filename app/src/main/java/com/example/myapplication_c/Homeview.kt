package com.example.myapplication_c

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Homeview(val repo: Repo): ViewModel() {
    private val _items :MutableLiveData<List<allitems>> = MutableLiveData()
    val items:LiveData<List<allitems>> = _items

    init{
allitems()
    }
fun allitems(){
    viewModelScope.launch {
        val a = repo.getallitems()
        _items.postValue(a)
    }
}

    fun getaddeditems() = repo.getaddeditems()

    fun additem(item:allitems) = CoroutineScope(Dispatchers.Main).launch {
        repo.additem(item)
    }


}