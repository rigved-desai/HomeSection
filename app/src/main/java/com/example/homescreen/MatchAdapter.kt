package com.example.homescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MatchAdapter(private val profileList : ArrayList<UserModel>) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = profileList[position]
        holder.bindData(profile)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val userName : TextView = itemView.findViewById(R.id.user_name)
        private val userAge: TextView = itemView.findViewById(R.id.user_age)
        private val userDistance : TextView = itemView.findViewById(R.id.user_dist)
        private val userImage: ImageView = itemView.findViewById(R.id.user_image)
        private val userOccupation : TextView = itemView.findViewById(R.id.user_occupation)

        fun bindData(profile: UserModel) {
            userName.text = profile.name
            userAge.text = profile.age.toString()
            userDistance.text = "${profile.distance.toString()} miles"
            userOccupation.text = profile.occupation
            userImage.setImageResource(profile.image)

//            Glide.with(itemView).load(profile.image).into(userImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }
}