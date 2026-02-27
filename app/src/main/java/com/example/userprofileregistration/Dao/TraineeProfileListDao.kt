package com.example.userprofileregistration.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.userprofileregistration.Entities.TraineeProfileList


@Dao
interface TraineeProfileListDao {
    @Insert
    fun insertProfileList(profile: TraineeProfileList)

    @Delete
    fun deleteProfileList(profile: TraineeProfileList)

    @Update
    fun updateProfileList(profile: TraineeProfileList)

    @Query("SELECT * FROM trainee_profile_list")
    fun getAllProfilesList(): List<TraineeProfileList>

    @Query("SELECT * FROM trainee_profile_list WHERE profileId = :id")
    fun getProfileListById(id: Int): TraineeProfileList?

}