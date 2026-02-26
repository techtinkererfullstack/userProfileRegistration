package com.example.userprofileregistration.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userprofileregistration.Dao.ProfileDetailsDao
import com.example.userprofileregistration.Dao.ProfileListDao
import com.example.userprofileregistration.Entities.ProfileDetails
import com.example.userprofileregistration.Entities.ProfileList

@Database([ProfileList::class, ProfileDetails::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun profileListDao(): ProfileListDao
    abstract fun profileDetailsDao(): ProfileDetailsDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "user_profile_database"
                ).allowMainThreadQueries().build()
            }
            return instance!!
        }
    }

}