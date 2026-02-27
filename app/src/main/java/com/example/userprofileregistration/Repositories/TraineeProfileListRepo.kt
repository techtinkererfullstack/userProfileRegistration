package com.example.userprofileregistration.Repositories

import com.example.userprofileregistration.Dao.TraineeProfileListDao
import com.example.userprofileregistration.Entities.TraineeProfileList


class TraineeProfileListRepo(private val traineeProfileListDao: TraineeProfileListDao) {

    fun getAllProfilesList(): List<TraineeProfileList> {
        return traineeProfileListDao.getAllProfilesList()
    }

    fun getProfileListById(id: Int): TraineeProfileList? {
        return traineeProfileListDao.getProfileListById(id)
    }
    fun insertProfileList(profile: TraineeProfileList) {
        traineeProfileListDao.insertProfileList(profile)
    }
    fun deleteProfileList(profile: TraineeProfileList) {
        traineeProfileListDao.deleteProfileList(profile)
    }
    fun updateProfileList(profile: TraineeProfileList) {
        traineeProfileListDao.updateProfileList(profile)
    }
}