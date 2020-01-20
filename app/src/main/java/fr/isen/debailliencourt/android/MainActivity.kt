package fr.isen.debailliencourt.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val username = UserEdit.text.toString()
            val password = PassEdit.text.toString()
            val invalidMessage = "$username/$password is not valid"

            if(username == "admin" && password=="123")
            {
                Toast.makeText(this, "ok", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, invalidMessage, Toast.LENGTH_LONG).show()
            }


        }
    }
}
