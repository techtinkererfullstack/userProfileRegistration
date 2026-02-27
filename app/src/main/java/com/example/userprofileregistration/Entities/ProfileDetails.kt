package com.example.userprofileregistration.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "profile_details",foreignKeys = [
    ForeignKey(
        entity = TrainerProfileList::class,
        parentColumns = ["profileId"],
        childColumns = ["trainerProfileId"],
        onDelete = ForeignKey.CASCADE // This deletes details when trainer is deleted
    )
],
    indices = [Index(value = ["trainerProfileId"])])
data class ProfileDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val trainerProfileId: Int,
    val aboutMe: String,
)
