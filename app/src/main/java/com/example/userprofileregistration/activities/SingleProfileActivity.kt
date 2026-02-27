    package com.example.userprofileregistration.activities

    import android.os.Bundle
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import com.example.userprofileregistration.R
    import com.example.userprofileregistration.databinding.ActivitySingleProfileBinding

    class SingleProfileActivity : AppCompatActivity() {
        private lateinit var binding: ActivitySingleProfileBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            binding = ActivitySingleProfileBinding.inflate(layoutInflater)
            setContentView(binding.root)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            val type = intent.getStringExtra("profileType")

            if (type == "TRAINER") {
                // Bind Trainer fields
                val trainerName = intent.getStringExtra("trainerName")
                val trainerDescription = intent.getStringExtra("trainerDescription")
                val trainerFollowers = intent.getStringExtra("trainerFollowers")
                val trainerPosts = intent.getStringExtra("trainerPosts")
                val trainerImg = intent.getStringExtra("trainerImg")
                binding.nameTV.text = trainerName
                binding.professionTV.text = trainerDescription
                binding.followersTV.text = trainerFollowers
                binding.postsTV.text = trainerPosts
                binding.profileImage.setImageURI(android.net.Uri.parse(trainerImg))

            } else {
                // Bind Trainee fields
                val traineeName = intent.getStringExtra("traineeName")
                val traineeDescription = intent.getStringExtra("traineeDescription")
                val traineeFollowers = intent.getStringExtra("traineeFollowers")
                val traineePosts = intent.getStringExtra("traineePosts")
                val traineeImg = intent.getStringExtra("traineeImage")
                binding.nameTV.text = traineeName
                binding.professionTV.text = traineeDescription
                binding.followersTV.text = traineeFollowers
                binding.postsTV.text = traineePosts
                binding.profileImage.setImageURI(android.net.Uri.parse(traineeImg))

            }



        }
    }