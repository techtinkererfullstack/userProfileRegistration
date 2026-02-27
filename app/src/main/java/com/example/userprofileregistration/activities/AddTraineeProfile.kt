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
    private var traineeProfileId: Int = -1
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

        traineeProfileId = intent.getIntExtra("traineeProfileId", -1)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddTraineeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        viewModel = ViewModelProvider(this)[TraineeProfileListViewModel::class.java]


        binding.traineeImagePicker.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        if (traineeProfileId != -1) {
            binding.nameET.setText(intent.getStringExtra("name"))
            binding.detailsET.setText(intent.getStringExtra("description"))
            binding.followersET.setText(intent.getStringExtra("followers"))
            binding.postET.setText(intent.getStringExtra("posts"))

            val imageUri = intent.getStringExtra("profileImage")
            if (!imageUri.isNullOrEmpty()) {
                selectedImageUri = imageUri
                binding.ivProfileImage.setImageURI(Uri.parse(imageUri))
            }
        }


        binding.btnSave.setOnClickListener {

            val name = binding.nameET.text.toString()
            val description = binding.detailsET.text.toString()
            val followers = binding.followersET.text.toString()
            val posts = binding.postET.text.toString()
            val profileImage = selectedImageUri


            if (selectedImageUri.isNotEmpty()) {
                binding.ivProfileImage.setImageURI(Uri.parse(selectedImageUri))
            }


            // 1. Check if we have a valid profileId (meaning we are in Edit Mode)
            if (traineeProfileId != -1) {
                // UPDATE MODE
                val profile = TraineeProfileList(
                    profileId = traineeProfileId, // Pass the existing ID!
                    name = name,
                    description = description,
                    followers = followers,
                    posts = posts,
                    profileImage = profileImage
                )
                viewModel.updateTraineeProfileList(profile)
            } else {
                // INSERT MODE (New Card)
                val profile = TraineeProfileList(
                    name = name,
                    description = description,
                    followers = followers,
                    posts = posts,
                    profileImage = profileImage
                )
                viewModel.insertTraineeProfileList(profile)
            }


            finish()

        }
    }
}