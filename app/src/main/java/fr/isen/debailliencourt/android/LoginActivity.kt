package fr.isen.debailliencourt.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        UserEdit.setOnClickListener {
            val identifiant = UserEdit.text.toString()
            val message = "tu as cliqué $identifiant"

            val toast = Toast.makeText(this, message, Toast.LENGTH_LONG).show()


        }
    }
}
