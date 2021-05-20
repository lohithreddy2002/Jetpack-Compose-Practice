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
    var quantity:Int = 0
    )

@Dao
interface dataInter {

    @Query("SELECT * FROM  ALLITEMS")
    fun getaddeditems() : LiveData<List<allitems>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun additem(item:allitems)

    @Update
    suspend fun updateitem(item:allitems)

    @Delete
    suspend fun deleteitem(item: allitems)
}

interface appInter{
    suspend fun getallitems():List<allitems>
}



@Database(entities = [allitems::class],version = 2)
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
