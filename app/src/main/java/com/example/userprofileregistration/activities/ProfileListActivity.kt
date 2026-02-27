package com.example.userprofileregistration.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userprofileregistration.Entities.TrainerProfileList
import com.example.userprofileregistration.R
import com.example.userprofileregistration.ViewModels.TraineeProfileListViewModel
import com.example.userprofileregistration.ViewModels.TrainerProfileListViewModel
import com.example.userprofileregistration.adapters.TraineeProfileListAdapter
import com.example.userprofileregistration.adapters.TrainerProfileListAdapter
import com.example.userprofileregistration.databinding.ActivityProfileListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
                val intent = Intent(this, AddTrainerProfileActivity::class.java)
                intent.putExtra("trainerProfileId", it.profileId)
                intent.putExtra("name", it.name)
                intent.putExtra("description", it.description)
                intent.putExtra("followers", it.followers)
                intent.putExtra("posts", it.posts)
                intent.putExtra("profileImage", it.profileImage)

                startActivity(intent)
            }, onDeleteClick = {trainerProfile->
                val view = layoutInflater.inflate(R.layout.dialog_delete, null)
                val dialog = MaterialAlertDialogBuilder(this)
                    .setView(view)
                    .create()
                view.findViewById<Button>(R.id.btnConfirmDelete).setOnClickListener {
                    trainerViewModel.deleteProfileList(trainerProfile)
                    trainerViewModel.getAllProfilesList()
                    dialog.dismiss()
                }
                dialog.show()
            })
            binding.profileRecyclerViewTrainers.adapter = trainerAdapter
        }




        traineeViewModel.traineeProfileLiveData.observe(this) { traineeprofileList ->
            val traineeAdapter = TraineeProfileListAdapter(traineeprofileList,
                 onEditClick = {
                    val intent = Intent(this, AddTraineeProfile::class.java)
                    intent.putExtra("traineeProfileId", it.profileId)
                    intent.putExtra("name", it.name)
                    intent.putExtra("description", it.description)
                    intent.putExtra("followers", it.followers)
                    intent.putExtra("posts", it.posts)
                    intent.putExtra("profileImage", it.profileImage)

                    startActivity(intent)
            }, onDeleteClick = {traineProfile ->
                    val view = layoutInflater.inflate(R.layout.dialog_delete, null)
                    val dialog = MaterialAlertDialogBuilder(this)
                        .setView(view)
                        .create()
                    view.findViewById<Button>(R.id.btnConfirmDelete).setOnClickListener {
                        traineeViewModel.deleteTraineeProfileList(traineProfile)
                        traineeViewModel.getAllTraineeProfilesList()
                        dialog.dismiss()
                    }
                    dialog.show()
            }
            )
            binding.profileRecyclerViewTrainee.adapter = traineeAdapter
        }

    }

    override fun onResume() {
        super.onResume()
       trainerViewModel.getAllProfilesList()
       traineeViewModel.getAllTraineeProfilesList()

    }
}