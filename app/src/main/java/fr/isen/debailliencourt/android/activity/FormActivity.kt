package fr.isen.debailliencourt.android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import java.util.*
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import kotlinx.android.synthetic.main.activity_form.*
import android.app.AlertDialog
import android.widget.DatePicker
import android.widget.Toast
import fr.isen.debailliencourt.android.R
import org.json.JSONObject
import java.io.File


class FormActivity : AppCompatActivity() {

    var textViewDate: TextView? = null


    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val alert = AlertDialog.Builder(this)
        alert.setTitle(R.string.form_alert)


        textViewDate = this.text_view_date_1

        textViewDate!!.text = "--/--/----"

        val dateSetListener = DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, day)
            updateDateInView()
        }


        buttonRead.setOnClickListener{
            showDataFromFile()
        }

        buttonSave.setOnClickListener {
            saveDataToFile(
                textInputSurname.text.toString(),
                textInputName.text.toString(),
                text_view_date_1.text.toString(),
                getAge(
                    cal.get(Calendar.DAY_OF_MONTH),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.YEAR)
                )
            )
        }

        textViewDate?.setOnClickListener {
            DatePickerDialog(this@FormActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun saveDataToFile(lastName: String, firstName: String, date: String, age: Int){
        val data = "{$LAST_NAME_KEY: '$lastName', $FIRST_NAME_KEY: '$firstName', $DATE_KEY: '$date', $AGE_KEY: '$age'}"

        File(cacheDir.absolutePath + JSON_FILE).writeText(data)
        Toast.makeText(this, "Données sauvegardé", Toast.LENGTH_SHORT).show()
    }

    private fun showDataFromFile(){
        val dataJson: String = File(cacheDir.absolutePath + JSON_FILE).readText()

        if(dataJson.isNotEmpty()){
            val jsonObject = JSONObject(dataJson)

            val strDate: String = jsonObject.optString(DATE_KEY)
            val strLastName: String = jsonObject.optString(LAST_NAME_KEY)
            val strFirstName: String = jsonObject.optString(FIRST_NAME_KEY)
            val strAge: String = jsonObject.optString(AGE_KEY)

            AlertDialog.Builder(this)
                .setTitle("Données")
                .setMessage("Nom: $strLastName\n Prénom: $strFirstName\n Date : $strDate\n Age : $strAge")
                .create()
                .show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.FRENCH)
        textViewDate!!.text = sdf.format(cal.time)
    }

    private fun getAge(day: Int, month: Int, year: Int):Int{
        val dateOfBirth = Calendar.getInstance()
        val today = Calendar.getInstance()

        dateOfBirth.set(year,month,day)

        var age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR)

        if((today.get(Calendar.DAY_OF_MONTH) < dateOfBirth.get(Calendar.DAY_OF_MONTH) &&
            today.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH)) ||
            (today.get(Calendar.MONTH) < dateOfBirth.get(Calendar.MONTH)))
        {
            return age -1
        }
        else{
            return age
        }

    }

    companion object{
        private const val JSON_FILE = "data.json"
        private const val LAST_NAME_KEY = "lastName"
        private const val FIRST_NAME_KEY = "firstName"
        private const val DATE_KEY = "date"
        private const val AGE_KEY = "age"
    }


}
