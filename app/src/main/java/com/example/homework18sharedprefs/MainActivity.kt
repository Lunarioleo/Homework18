package com.example.homework18sharedprefs

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var temp: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btnCounter)
        val count = findViewById<TextView>(R.id.text)
        val reset = findViewById<ImageView>(R.id.reset)

        temp = retrieveData()!!.toInt()
        count.text = temp.toString()

        btn.setOnClickListener {
            temp++
            count.text = temp.toString()
        }

        reset.setOnClickListener {
            temp = 0
            count.text = temp.toString()
        }


    }

    override fun onPause() {
        saveData(temp)
        super.onPause()
    }

    private fun saveData(countToSave: Int){
        val sharedPref: SharedPreferences = getSharedPreferences(KEY_1, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(KEY_1, countToSave.toString())
        editor.apply()
    }

    private fun retrieveData(): String? {
        val sharedPref: SharedPreferences = getSharedPreferences(KEY_1, MODE_APPEND)
        return sharedPref.getString(KEY_1, "")
    }


    companion object{
        const val KEY_1 = "Counter"
    }
}