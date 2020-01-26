package fr.isen.debailliencourt.android.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object{
        //camera request code
        private const val REQUEST_CAMERA: Int  = 1
        //image pick code
        private const val IMAGE_PICK_CODE = 1000;
        //Permission code
        private const val PERMISSION_CODE = 1001;
    }

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
            //system OS is > Marshmallow
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA) ==                  PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==   PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==  PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==    PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==  PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.READ_CONTACTS) ==           PackageManager.PERMISSION_DENIED){

                    //permission denied
                    val permissions = arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_CONTACTS)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PermissionActivity.PERMISSION_CODE)
                }
                else{
                    //permission already granted
                    startActivity(permIntent)
                }
            }
            else{
                //system OS is < Marshmallow
                startActivity(permIntent)
            }

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
