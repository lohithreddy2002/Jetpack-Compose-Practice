package com.example.myapplication_c

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.RoomDatabase


@Entity
data class allitems (
    @PrimaryKey val id : Int,
    val item_name :String,
    val price:Int,
    )

@Dao
interface dataInter {

    @Query("SELECT * FROM  ALLITEMS")
    fun getaddeditems() : LiveData<List<allitems>>

    @Insert
    suspend fun additem(item:allitems)
}

interface appInter{
    suspend fun getallitems():List<allitems>
}



@Database(entities = [allitems::class],version = 1)
abstract class database:RoomDatabase(){

abstract fun getdao():dataInter

    companion object {
        @Volatile
        private var instance:database? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            database::class.java,
            "covid_care_db.db"
        ).fallbackToDestructiveMigration().build()

    }
}
