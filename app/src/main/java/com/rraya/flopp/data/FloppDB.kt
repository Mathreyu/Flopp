package com.rraya.flopp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rraya.flopp.data.models.VideoGameDao
import com.rraya.flopp.data.models.VideoGameDetailDao
import com.rraya.flopp.data.models.VideoGameDetails
import com.rraya.flopp.data.models.VideoGameLight

@Database(entities = [VideoGameLight::class, VideoGameDetails::class], version = 3, exportSchema = false)
abstract class FloppDB: RoomDatabase() {
    abstract fun videoGameLightDao(): VideoGameDao
    abstract fun detailsDao(): VideoGameDetailDao
    companion object {

        @Volatile private var instance: FloppDB? = null

        fun getInstance(context: Context): FloppDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FloppDB {
            return Room.databaseBuilder(context, FloppDB::class.java, "FLOPP_DB")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}