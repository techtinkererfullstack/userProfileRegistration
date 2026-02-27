package com.example.userprofileregistration.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trainer_profile_list")
data class TrainerProfileList(
    @PrimaryKey(autoGenerate = true)
    val profileId: Int = 0,
    val name:String,
    val description:String,
    val followers:String,
    val posts:String,
    val profileImage:String,

)
