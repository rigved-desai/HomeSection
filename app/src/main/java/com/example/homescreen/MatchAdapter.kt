package com.example.homescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MatchAdapter(private  val profileList : ArrayList<UserModel>, private val listener: CardClickListener, private val context : Context) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = profileList[position]
        holder.bindData(profile)
        holder.bindListener(listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val userNameAge : TextView = itemView.findViewById(R.id.user_name_age)
        private val userDistance : TextView = itemView.findViewById(R.id.user_dist)
        private val userImage: ImageView = itemView.findViewById(R.id.user_image)
        private val userOccupation : TextView = itemView.findViewById(R.id.user_occupation)

        fun bindListener(listener: CardClickListener) {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClicked(profileList[position])
                }
            }
        }
        fun bindData(profile: UserModel) {
            userNameAge.text = "${profile.name}, ${profile.age.toString()}"
            userDistance.text = "${profile.distance.toString()} miles"
            userOccupation.text = profile.occupation
            userImage.setImageResource(profile.image)
//            Use Glide to set user image into view when using image URl instead of drawable
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