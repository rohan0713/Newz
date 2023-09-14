package com.kotlin.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlin.newsapp.data.model.Article

@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao() : NewsDao

    companion object{

        // All reader threads will see the updated value of the volatile variable after completing the write operation.
        // If you are not using the volatile keyword, different reader thread may see different values.
        @Volatile
        private var instance : ArticleDatabase? = null

        // By using a custom lock object, we can ensure that only one thread can access the database at a time,
        // regardless of how many instances of the Database class are created.
        private val lock = Any()

        // If you want to execute a function without specifying the function name then you can define invoke the function
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "articleDB.db"
            ).build()

    }
}