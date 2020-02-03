package fr.isen.debailliencourt.android.activity

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.debailliencourt.android.R
import fr.isen.debailliencourt.android.dataClass.RandomUsersData

class RandomUsersAdapter(
    private val randomUsers: RandomUsersData
) : RecyclerView.Adapter<RandomUsersAdapter.ViewHolderRandom>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRandom {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.random_user, parent, false)
        Log.d("Tag", "onCreateViewHolderRandomUser")
        return ViewHolderRandom(view)
    }

    override fun getItemCount() = randomUsers.results.size

    override fun onBindViewHolder(holder: ViewHolderRandom, position: Int) {
        holder.randomUserName.text = randomUsers.results[position].name.first + "  " + randomUsers.results[position].name.last
        holder.randomUserAddress.text = randomUsers.results[position].location.city + "  " + randomUsers.results[position].location.postcode
        holder.randomUserMail.text = randomUsers.results[position].email
        Log.d("Tag", "onBindViewHolderRandomUser")
    }

    class ViewHolderRandom(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val randomUserName: TextView = itemView.findViewById(R.id.randomUserName)
        val randomUserMail: TextView = itemView.findViewById(R.id.randomUserMail)
        val randomUserAddress: TextView = itemView.findViewById(R.id.randomUserAddress)
        val randomUsersImage: ImageView = itemView.findViewById((R.id.imageViewRandomUser))
    }


}