package com.example.userprofileregistration.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.userprofileregistration.Entities.TraineeProfileList
import com.example.userprofileregistration.Entities.TrainerProfileList
import com.example.userprofileregistration.R
import com.example.userprofileregistration.ViewModels.TraineeProfileListViewModel
import com.example.userprofileregistration.ViewModels.TrainerProfileListViewModel
import com.example.userprofileregistration.databinding.ActivityAddTraineeProfileBinding
import com.example.userprofileregistration.databinding.ActivityAddTrainerProfileBinding

class AddTraineeProfile : AppCompatActivity() {
    private lateinit var binding: ActivityAddTraineeProfileBinding
    private lateinit var viewModel: TraineeProfileListViewModel
    private var profileId: Int = -1
    private var selectedImageUri: String = ""

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            this.contentResolver.takePersistableUriPermission(uri, flag)
            selectedImageUri = uri.toString()
            binding.ivProfileImage.setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_trainer_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityAddTraineeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.traineeImagePicker.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        viewModel = ViewModelProvider(this)[TraineeProfileListViewModel::class.java]

        binding.btnSave.setOnClickListener {

            val name = binding.nameET.text.toString()
            val description = binding.detailsET.text.toString()
            val followers = binding.followersET.text.toString()
            val posts = binding.postET.text.toString()

            val profile = TraineeProfileList(
                name = name,
                description = description,
                followers = followers,
                posts = posts,
                profileImage = selectedImageUri
            )
            viewModel.insertTraineeProfileList(profile)

            finish()

        }
    }
}