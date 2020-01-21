package fr.isen.debailliencourt.android.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    companion object{
        private const val GOOD_ID: String = "admin"
        private const val GOOD_PASS: String  ="1234"
        const val LOGIN_PREF: String = "login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE)
        val savedIdentifiant: String? = sharedPreferences.getString("id", "")
        val savedMdp: String? = sharedPreferences.getString("mdp","")


        if(savedIdentifiant == GOOD_ID && savedMdp == GOOD_PASS){
            goToHome()
        }

        button.setOnClickListener{
            doLogin()
        }
    }

    private fun doLogin(){
        val username = UserEdit.text.toString()
        val password = PassEdit.text.toString()

        val invalidMessage = "username/password is not valid"

        if(username == GOOD_ID && password== GOOD_PASS)
        {
            saveUserCredential(username,password)
            Toast.makeText(this,"Data Stored",Toast.LENGTH_LONG).show()
            goToHome()
        }
        else{
            Toast.makeText(this, invalidMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun saveUserCredential(identifiant: String, mdp: String){
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("id",identifiant)
        editor.putString("mdp",mdp)
        editor.apply()
    }

    private fun goToHome(){
        val homeIntent : Intent =  Intent(this,
            HomeActivity::class.java)
        startActivity(homeIntent)
    }
}