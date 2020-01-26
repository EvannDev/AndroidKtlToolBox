package fr.isen.debailliencourt.android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import java.util.*
import android.app.DatePickerDialog
import android.widget.DatePicker
import java.text.SimpleDateFormat
import kotlinx.android.synthetic.main.activity_form.*
import android.app.AlertDialog
import android.util.Log
import android.widget.Toast
import fr.isen.debailliencourt.android.JSON
import fr.isen.debailliencourt.android.R
import fr.isen.debailliencourt.android.DataUser

class FormActivity : AppCompatActivity() {

    var buttonDate: Button? = null
    var textviewDate: TextView? = null

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val alert = AlertDialog.Builder(this)
        alert.setTitle(R.string.form_alert)


        textviewDate = this.text_view_date_1
        buttonDate = this.button_date_1

        textviewDate!!.text = "--/--/----"

        // create an OnDateSetListener
        val dateSetListener = DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, day)
                updateDateInView()
        }

        buttonRead.setOnClickListener{
            val user = JSON(cacheDir.absolutePath).load()

            val birthday = SimpleDateFormat("dd/MM/yyyy").parse(user.birthday).time
            val now = Calendar.getInstance().timeInMillis

            var years = 0L
            if (now > birthday) {
                val diff = now - birthday
                val seconds = diff / 1000
                val minutes = seconds / 60
                val hours = minutes / 60
                val days = hours / 24
                years = (days / 365)
            }

            alert.setMessage("Surname: ${user.surname}\nName: ${user.name}\nAge: $years").create().show()
        }

        buttonSave.setOnClickListener {
            Toast.makeText(this, "Save", Toast.LENGTH_LONG)
            Log.d("TAG", JSON(cacheDir.absolutePath).save(DataUser(textInputName.text.toString(), textInputSurname.text.toString(), text_view_date_1.text.toString())).load().toString())
        }

        buttonDate!!.setOnClickListener {
            DatePickerDialog(this@FormActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.FRENCH)
        textviewDate!!.text = sdf.format(cal.getTime())
    }


}
