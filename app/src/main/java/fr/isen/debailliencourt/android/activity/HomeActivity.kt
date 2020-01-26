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

        val cycleIntent =  Intent(this,
            LifeCycleActivity::class.java)

        layoutLifeCycle.setOnClickListener{
            startActivity(cycleIntent)
        }


        val saveIntent =  Intent(this,
            FormActivity::class.java)

        layoutSave.setOnClickListener{
            startActivity(saveIntent)
        }

        val permIntent =  Intent(this,
            PermissionActivity::class.java)

        layoutPerm.setOnClickListener{
            startActivity(permIntent)
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
