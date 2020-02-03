package fr.isen.debailliencourt.android.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.debailliencourt.android.R
import fr.isen.debailliencourt.android.dataClass.RandomUsersData
import kotlinx.android.synthetic.main.activity_permissions.*
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    private fun displayRandomUsers() {
        val queue = Volley.newRequestQueue(this)
        val url =
            "https://randomuser.me/api/?inc=name,location,email&results=15&noinfo&nat=fr&format=pretty"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val randomUsersObj =
                    Gson().fromJson(response.toString(), RandomUsersData::class.java)

                Log.d("RESPONSE API", response)
                recyclerViewRandomUsers.layoutManager = LinearLayoutManager(this)
                recyclerViewRandomUsers.adapter = RandomUsersAdapter(randomUsersObj)
            },
            Response.ErrorListener { titleWebActivity.text = "That didn't work!" })

        queue.add(stringRequest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        buttonGenerateRandomUsers.setOnClickListener {
            displayRandomUsers()
        }
        Log.d("STATUS", "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d("STATUS", "onResume")
    }
}
