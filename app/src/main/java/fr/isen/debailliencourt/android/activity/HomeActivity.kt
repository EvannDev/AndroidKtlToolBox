package fr.isen.debailliencourt.android.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPreferences = getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)

        val cycleIntent : Intent =  Intent(this,
            LifeCycleActivity::class.java)

        imCycleVie.setOnClickListener{
            startActivity(cycleIntent)
        }
        imRCycleVie.setOnClickListener{
            startActivity(cycleIntent)
        }
        textCycleVie.setOnClickListener{
            startActivity(cycleIntent)
        }

        val saveIntent : Intent =  Intent(this,
            FormActivity::class.java)

        imSave.setOnClickListener{
            startActivity(saveIntent)
        }
        imRSave.setOnClickListener{
            startActivity(saveIntent)
        }
        textSave.setOnClickListener{
            startActivity(saveIntent)
        }

        buttonLogOut.setOnClickListener{
            Toast.makeText(this, "Logout", Toast.LENGTH_LONG)
            doLogout()
        }
    }

    private fun doLogout(){
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        val loginIntent : Intent =  Intent(this,
            LoginActivity::class.java)
        startActivity(loginIntent)
    }
}
