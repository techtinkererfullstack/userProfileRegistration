package com.example.userprofileregistration.Database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database([ProfileList::class, ProfileDetails::class], verion=1)
abstract class AppDatabase(): RoomDatabase() {
}