package fr.isen.debailliencourt.android.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPreferences = getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)

        val cycleIntent = Intent(
            this,
            LifeCycleActivity::class.java
        )

        layoutLifeCycle.setOnClickListener {
            startActivity(cycleIntent)
        }

        val saveIntent = Intent(
            this,
            FormActivity::class.java
        )

        layoutSave.setOnClickListener {
            startActivity(saveIntent)
        }

        val permIntent = Intent(
            this,
            PermissionActivity::class.java
        )

        layoutPerm.setOnClickListener {
            //system OS is > Marshmallow
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_DENIED
            ) {

                //permission denied
                val permissions = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_CONTACTS
                )
                //show popup to request runtime permission
                ActivityCompat.requestPermissions(
                    this,
                    permissions,
                    PermissionActivity.PERMISSION_CODE
                )
            } else {
                //permission already granted
                startActivity(permIntent)
            }
        }

        val webIntent = Intent(
            this,
            WebActivity::class.java
        )

        layoutWeb.setOnClickListener {
            startActivity(webIntent)
        }

        buttonLogOut.setOnClickListener {
            Toast.makeText(this, "Logout", Toast.LENGTH_LONG)
            doLogout()
        }
    }


    private fun doLogout() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        val loginIntent: Intent = Intent(
            this,
            LoginActivity::class.java
        )
        startActivity(loginIntent)
    }
}
