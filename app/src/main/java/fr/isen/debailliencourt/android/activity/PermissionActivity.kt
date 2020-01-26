package fr.isen.debailliencourt.android.activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fr.isen.debailliencourt.android.R

class PermissionActivity : AppCompatActivity() {
    companion object{
        private const val REQUEST_CAMERA: Int  = 1
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            // Permissions Granted

            Toast.makeText(this, "Permissions Granted", Toast.LENGTH_SHORT).show()
        }
        else{
            //Permissions hasn't been granted

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
                Toast.makeText(this, "Camera permission is needed, trust me", Toast.LENGTH_SHORT).show()
            }
            val permissionsList = arrayOf("Manifest.permission.Camera")
            requestPermissions(permissionsList, REQUEST_CAMERA)
        }
    }
}
