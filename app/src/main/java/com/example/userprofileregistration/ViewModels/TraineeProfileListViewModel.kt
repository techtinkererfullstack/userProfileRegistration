package com.example.userprofileregistration.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.userprofileregistration.Database.AppDatabase
import com.example.userprofileregistration.Entities.TraineeProfileList
import com.example.userprofileregistration.Repositories.TraineeProfileListRepo

class TraineeProfileListViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TraineeProfileListRepo
    val traineeProfileLiveData = MutableLiveData<List<TraineeProfileList>>()

    init {
        val dao = AppDatabase.getDatabase(application).traineeProfileListDao()
        repository = TraineeProfileListRepo(dao)
        getAllTraineeProfilesList()
    }

    fun getAllTraineeProfilesList() {
        traineeProfileLiveData.value = repository.getAllProfilesList()
    }

    fun insertTraineeProfileList(profile: TraineeProfileList) {
        repository.insertProfileList(profile)
        getAllTraineeProfilesList()
    }

    fun deleteTraineeProfileList(profile: TraineeProfileList) {
        repository.deleteProfileList(profile)
        getAllTraineeProfilesList()
    }
    fun updateTraineeProfileList(profile: TraineeProfileList) {
        repository.updateProfileList(profile)
        getAllTraineeProfilesList()
    }

    fun getTraineeProfileListById(id: Int): TraineeProfileList? {
        return repository.getProfileListById(id)
    }

}