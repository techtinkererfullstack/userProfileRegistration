package com.example.userprofileregistration.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.userprofileregistration.Entities.TrainerProfileList


@Dao
interface TrainerProfileListDao {
    @Insert
    fun insertProfileList(profile: TrainerProfileList)

    @Delete
    fun deleteProfileList(profile: TrainerProfileList)

    @Update
    fun updateProfileList(profile: TrainerProfileList)

    @Query("SELECT * FROM trainer_profile_list")
    fun getAllProfilesList(): List<TrainerProfileList>

    @Query("SELECT * FROM trainer_profile_list WHERE profileId = :id")
    fun getProfileListById(id: Int): TrainerProfileList?



}