package com.imskanand.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
            Toast.makeText(this, "DatePicker Clicked", Toast.LENGTH_LONG).show()
        }
    }

    fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,"The Chosen Year is $selectedYear, Month is $selectedMonth, Day is $selectedDayOfMonth",
                    Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedYear/${selectedMonth+1}/$selectedDayOfMonth"
                val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
                tvSelectedDate.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = (theDate!!.time / 60000)
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = (currentDate!!.time / 60000)

                val differenceInMinutes =  (currentDateInMinutes - selectedDateInMinutes)

                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                tvSelectedDateInMinutes.text = differenceInMinutes.toString()
            },
            year,
            month,
            day).show()
    }
}