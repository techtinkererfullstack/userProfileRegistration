package com.example.userprofileregistration.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofileregistration.Entities.TraineeProfileList
import com.example.userprofileregistration.Entities.TrainerProfileList
import com.example.userprofileregistration.R
import com.example.userprofileregistration.activities.SingleProfileActivity
import com.example.userprofileregistration.databinding.ProfileCardBinding


class TraineeProfileListAdapter(
    private val traineeProfileList: List<TraineeProfileList>,
    private val onEditClick: (TraineeProfileList) -> Unit,
    private val onDeleteClick: (TraineeProfileList) -> Unit
): RecyclerView.Adapter<TraineeProfileListAdapter.ProfileViewHolder>() {


    class ProfileViewHolder( val binding: ProfileCardBinding): RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
       val binding = ProfileCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        val traineeProfile = traineeProfileList[position]
        holder.binding.profileName.text = traineeProfile.name
        holder.binding.details.text = traineeProfile.description
        holder.binding.followers.text = "👤 ${ traineeProfile.followers }"
        holder.binding.posts.text = "\uD83D\uDDBC\uFE0F ${ traineeProfile.posts }"
        holder.binding.btnMenu.setOnClickListener {
            showPopupMenu(it, traineeProfile)
        }

        holder.binding.root.setOnClickListener {
            val context = holder.binding.root.context
            val intent = Intent(context, SingleProfileActivity::class.java)
            intent.putExtra("traineeProfileId", traineeProfile.profileId)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = traineeProfileList.size


    private fun showPopupMenu(view: View, profile: TraineeProfileList) {

        val popup = PopupMenu(view.context, view)
        popup.menuInflater.inflate(R.menu.card_menu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_edit -> {
                    onEditClick(profile)
                    true
                }
                R.id.menu_delete -> {
                    onDeleteClick(profile)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

}