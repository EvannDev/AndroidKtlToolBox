package fr.isen.debailliencourt.android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.debailliencourt.android.fragments.LifeCycleFrag
import fr.isen.debailliencourt.android.fragments.LifeCycleFrag2
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.activity_life_cycle.*


class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        lifeCycleText.text = "onCreate"

        val frag1 = LifeCycleFrag()
        val frag2 = LifeCycleFrag2()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.layoutFragment, frag1)
        transaction.commit()

        buttonFrag.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.layoutFragment, (if (frag1.isVisible) frag2 else frag1)).commit()

        }
    }

    private fun notification(message: String, isActive: Boolean) {
        if (isActive)
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
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show()
    }
}
