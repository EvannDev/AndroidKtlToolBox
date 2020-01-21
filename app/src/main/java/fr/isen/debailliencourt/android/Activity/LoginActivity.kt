package fr.isen.debailliencourt.android.Activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        UserEdit.setOnClickListener {
            val identifiant = UserEdit.text.toString()
            val message = "tu as cliqué $identifiant"
            val sharedPref: SharedPreferences = getSharedPreferences("admin", Context.MODE_PRIVATE)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun getValueString(KEY_NAME: String, sharedPref:SharedPreferences): String? {
        return sharedPref.getString(KEY_NAME, null)

    }
}
