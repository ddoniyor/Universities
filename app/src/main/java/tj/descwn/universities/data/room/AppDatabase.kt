package tj.descwn.universities.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class],version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao():Dao

    companion object{
        private const val DATABASE_NAME = "favorites.db"
        @Volatile
        private var INSTANCE :AppDatabase?=null

        fun getInstance(context: Context):AppDatabase{
            if (INSTANCE !=null) return INSTANCE!!
            synchronized(this){
                INSTANCE = Room
                    .databaseBuilder(context,AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }

}