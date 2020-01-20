package fr.isen.debailliencourt.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cycle_vie.*

class CycleVie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_vie)

        lifeCycleText.text = "onCreate"
    }

    private fun notification(message : String, isActive : Boolean){
        if(isActive)
            lifeCycleText.text = message
        else
            Log.d("TAG", message)
    }

    override fun onStart() {
        super.onStart()
        notification("onStart", isActive = true)
    }

    override fun onResume() {
        super.onResume()
        notification("onResume", isActive = true)
    }

    override fun onPause() {
        super.onPause()
        notification("onPause", isActive = false)
    }

    override fun onStop() {
        super.onStop()
        notification("onStop", isActive = false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG)
    }
}
