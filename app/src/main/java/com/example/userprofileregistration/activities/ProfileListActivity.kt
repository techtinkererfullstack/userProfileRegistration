package com.example.userprofileregistration.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userprofileregistration.R
import com.example.userprofileregistration.ViewModels.TraineeProfileListViewModel
import com.example.userprofileregistration.ViewModels.TrainerProfileListViewModel
import com.example.userprofileregistration.adapters.TraineeProfileListAdapter
import com.example.userprofileregistration.adapters.TrainerProfileListAdapter
import com.example.userprofileregistration.databinding.ActivityProfileListBinding

class ProfileListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileListBinding
    private lateinit var trainerViewModel: TrainerProfileListViewModel
    private lateinit var traineeViewModel: TraineeProfileListViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.profileRecyclerViewTrainers.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,
            false)
        binding.profileRecyclerViewTrainee.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,
            false)
        trainerViewModel = ViewModelProvider(this)[TrainerProfileListViewModel::class.java]
        traineeViewModel = ViewModelProvider(this)[TraineeProfileListViewModel::class.java]

        binding.fabAddTrainerProfile.setOnClickListener {
            val intent = Intent(this, AddTrainerProfileActivity::class.java)
            startActivity(intent)
        }

        binding.fabAddTraineeProfile.setOnClickListener {
            val intent = Intent(this, AddTraineeProfile::class.java)
            startActivity(intent)
        }




        trainerViewModel.profileLiveData.observe(this) { profileList ->
            val trainerAdapter = TrainerProfileListAdapter(profileList, onEditClick = {
                trainerViewModel.updateProfileList(it)
            }, onDeleteClick = {
                trainerViewModel.deleteProfileList(it)
            })
            binding.profileRecyclerViewTrainers.adapter = trainerAdapter
        }




        traineeViewModel.traineeProfileLiveData.observe(this) { traineeprofileList ->
            val traineeAdapter = TraineeProfileListAdapter(traineeprofileList, onEditClick = {
                traineeViewModel.updateTraineeProfileList(it)
            }, onDeleteClick = {
                traineeViewModel.deleteTraineeProfileList(it)
            })
            binding.profileRecyclerViewTrainee.adapter = traineeAdapter
        }

    }

    override fun onResume() {
        super.onResume()
       trainerViewModel.getAllProfilesList()
       traineeViewModel.getAllTraineeProfilesList()

    }
}