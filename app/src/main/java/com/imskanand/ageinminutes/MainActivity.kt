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
        }
    }

    fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
                tvSelectedDate.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = (theDate!!.time / 60000)
                val selectedDateInDays = selectedDateInMinutes/ 1440
                val selectedDateInMonth = selectedDateInDays / 30

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = (currentDate!!.time / 60000)
                val currentDateInDays = currentDateInMinutes / 1440
                val currentDateInMonth = currentDateInDays / 30

                val differenceInMinutes =  (currentDateInMinutes - selectedDateInMinutes)
                val differenceInDays = (currentDateInDays - selectedDateInDays)
                val differenceInMonth = currentDateInMonth - selectedDateInMonth

                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                tvSelectedDateInMinutes.text = differenceInMinutes.toString()

                val tvSelectedDateInDays = findViewById<TextView>(R.id.tvSelectedDateInDays)
                tvSelectedDateInDays.text = differenceInDays.toString()

                val tvSelectedDateInMonth = findViewById<TextView>(R.id.tvSelectedDateInMonth)
                tvSelectedDateInMonth.text = differenceInMonth.toString()
            },
            year,
            month,
            day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}