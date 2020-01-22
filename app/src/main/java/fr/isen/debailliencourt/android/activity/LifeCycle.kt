package fr.isen.debailliencourt.android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.debailliencourt.android.fragments.LifeCycleFrag
import fr.isen.debailliencourt.android.fragments.LifeCycleFrag2
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.activity_life_cycle.*


class LifeCycle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        lifeCycleText.text = "onCreate"

        val frag1 = LifeCycleFrag()
        val frag2 = LifeCycleFrag2()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.activity_Life_Cycle,frag1)
        transaction.commit()

        buttonFrag.setOnClickListener{
            if(frag1.isResumed){
                supportFragmentManager.beginTransaction().add(R.id.activity_Life_Cycle,frag2).commit()
            }
            else if(frag2.isResumed){
                supportFragmentManager.beginTransaction().add(R.id.activity_Life_Cycle,frag1).commit()
            }
        }
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
