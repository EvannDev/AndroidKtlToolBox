package fr.isen.debailliencourt.android.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.debailliencourt.android.R
import fr.isen.debailliencourt.android.dataClass.Contact
import fr.isen.debailliencourt.android.dataClass.RandomUsersData
import kotlinx.android.synthetic.main.activity_web.*
import org.json.JSONObject

class WebActivity : AppCompatActivity() {

    private fun displayRandomUsers() {
        val users: ArrayList<String> = ArrayList()


        recyclerViewRandomUsers.layoutManager = LinearLayoutManager(this)
        recyclerViewRandomUsers.adapter = UsersAdapter(users)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val queue = Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api/?inc=name,location,email&noinfo&nat=fr&format=pretty"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                titleWebActivity.text=response
                var randomUserObj = JSONObject(response)
                val randomUsers: RandomUsersData = Gson().fromJson<RandomUsersData>(response,RandomUsersData::class.java)
                buttonGenerateRandomUsers.text=randomUsers?.result?.location?.country
            },
            Response.ErrorListener { titleWebActivity.text = "That didn't work!" })

        queue.add(stringRequest)

        buttonGenerateRandomUsers.setOnClickListener{
            Toast.makeText(this,"Test",Toast.LENGTH_LONG)
        }
        Log.d("STATUS","onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d("STATUS","onResume")
    }
}
