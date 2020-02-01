package fr.isen.debailliencourt.android.activity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.random_user.view.*

class RandomUsersAdapter(private val randomUsers: ArrayList<String>): RecyclerView.Adapter<RandomUsersAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.random_user, parent, false)
        Log.d("Tag", "onCreateViewHolder")
        return ViewHolder(view)
    }

    override fun getItemCount()= randomUsers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.randomUserName.text = randomUsers[position]
        Log.d("Tag", "onBindViewHolder")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val randomUserName: TextView = itemView.findViewById(R.id.randomUserName)
        val randomUserMail: TextView = itemView.findViewById(R.id.randomUserMail)
        val randomUserAddress: TextView = itemView.findViewById(R.id.randomUserAddress)
    }


}