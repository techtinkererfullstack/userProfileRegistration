package com.example.userprofileregistration.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.userprofileregistration.Entities.ProfileList


@Dao
interface ProfileListDao {
    @Insert
    fun insertProfileList(profile: ProfileList)

    @Delete
    fun deleteProfileList(profile: ProfileList)

    @Update
    fun updateProfileList(profile: ProfileList)

    @Query("SELECT * FROM profile_list")
    fun getAllProfilesList(): List<ProfileList>

    @Query("SELECT * FROM profile_list WHERE id = :id")
    fun getProfileListById(id: Int): ProfileList?



}