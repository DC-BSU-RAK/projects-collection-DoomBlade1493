package com.example.moodselector

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var moodGroup: RadioGroup
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        moodGroup = findViewById(R.id.moodGroup)
        saveBtn = findViewById(R.id.saveBtn)

        saveBtn.setOnClickListener {
            val selectedId = moodGroup.checkedRadioButtonId
            val selected = findViewById<RadioButton>(selectedId)

            if (selected != null) {
                val prefs: SharedPreferences = getSharedPreferences("MoodPrefs", MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("mood", selected.text.toString())
                editor.apply()
            }

            finish()
        }
    }
}
