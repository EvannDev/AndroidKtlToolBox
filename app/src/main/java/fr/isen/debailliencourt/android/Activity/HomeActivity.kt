package fr.isen.debailliencourt.android.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val cycleIntent : Intent =  Intent(this,
            LifeCycle::class.java)

        imCycleVie.setOnClickListener{
            startActivity(cycleIntent)
        }

        imRCycleVie.setOnClickListener{
            startActivity(cycleIntent)
        }

        textCycleVie.setOnClickListener{
            startActivity(cycleIntent)
        }


    }
}
