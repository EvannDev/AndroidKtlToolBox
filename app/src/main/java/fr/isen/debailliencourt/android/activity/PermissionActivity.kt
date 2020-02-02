package fr.isen.debailliencourt.android.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import com.google.android.gms.location.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.activity_permissions.*
import android.os.Looper
import  android.app.AlertDialog
import android.content.DialogInterface
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import fr.isen.debailliencourt.android.dataClass.Contact


class PermissionActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)

    companion object{
        //Permission code
        private const val IMAGE_PICK_REQUEST = 1000
        private const val CAMERA_PICK_REQUEST = 4444

        const val PERMISSION_CODE = 1002
    }


    lateinit var mFusedLocationClient: FusedLocationProviderClient

    private val backButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext, "Aucune photo choisie", Toast.LENGTH_SHORT).show()
    }


    private fun getLastLocation() {
        if (isLocationEnabled()) {

            mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                var location: Location? = task.result
                if (location == null) {
                    requestNewLocationData()
                } else {
                    valueLatitude.text = location.latitude.toString()
                    valueLongitude.text = location.longitude.toString()
                }
            }
        }
        else {
            Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            valueLatitude.text = mLastLocation.latitude.toString()
            valueLongitude.text = mLastLocation.longitude.toString()
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun withItems() {

        val items = arrayOf("Camera", "Galerie")
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("Choisir:")
            setItems(items) { dialog, which ->
                if(items[which] == "Camera"){
                    pickImageFromCamera()
                }
                else{
                    pickImageFromGallery()
                }
            }
            setPositiveButton("Retour", backButtonClick)
            show()
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_REQUEST)
    }

    private fun pickImageFromCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, CAMERA_PICK_REQUEST)
            }
        }
    }

    private fun displayContacts(){
        val users: ArrayList<String> = ArrayList()

        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,null)
        cursor?.let {
            while (it.moveToNext()) {
                users.add(
                    Contact(
                        it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    ).name + " : " +
                            Contact(
                                it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                                it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            ).phone
                )
            }
            it.close()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UsersAdapter(users)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        val floatingActionButton: View = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Test", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }

        //RecyclerView
        displayContacts()

        //Location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()

        //Pick Image
        pictGalleryPerm.setOnClickListener{
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==    PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==  PackageManager.PERMISSION_DENIED)
            {
                //permission denied
                val permissions = arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                //show popup to request runtime permission
                ActivityCompat.requestPermissions(this,permissions,PERMISSION_CODE)
            }
            else{
                //permission already granted
                withItems()
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission
                    pickImageFromGallery()
                }
                else if (grantResults.isNotEmpty() && grantResults[1] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromCamera()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_REQUEST){
            pictGalleryPerm.setImageURI(data?.data)
        }
        else if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_PICK_REQUEST){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            pictGalleryPerm.setImageBitmap(imageBitmap)
        }
    }
}
