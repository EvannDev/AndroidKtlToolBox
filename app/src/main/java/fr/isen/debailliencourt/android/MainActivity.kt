package fr.isen.debailliencourt.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {

    companion object{
        private const val GOOD_ID ="admin"
        private const val GOOD_PASS ="1234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin(){
        val username = UserEdit.text.toString()
        val password = PassEdit.text.toString()
        val invalidMessage = "username/password is not valid"

        val homeIntent : Intent =  Intent(this,HomeActivity::class.java)

        if(username == GOOD_ID && password== GOOD_PASS)
        {
            startActivity(homeIntent)
        }
        else{
            Toast.makeText(this, invalidMessage, Toast.LENGTH_LONG).show()
        }
    }
}
