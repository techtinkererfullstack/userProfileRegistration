package com.example.userprofileregistration.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userprofileregistration.Dao.TraineeProfileListDao
import com.example.userprofileregistration.Dao.TrainerProfileListDao
import com.example.userprofileregistration.Entities.TraineeProfileList
import com.example.userprofileregistration.Entities.TrainerProfileList

@Database([TrainerProfileList::class, TraineeProfileList::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trainerProfileListDao(): TrainerProfileListDao
    abstract fun traineeProfileListDao(): TraineeProfileListDao
//    abstract fun profileDetailsDao(): ProfileDetailsDao

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