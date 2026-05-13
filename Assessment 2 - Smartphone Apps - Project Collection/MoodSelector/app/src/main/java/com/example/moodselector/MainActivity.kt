package com.example.moodselector

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var moodText: TextView
    private lateinit var changeMoodBtn: Button
    private lateinit var instructionsBtn: Button
    private lateinit var mainLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moodText = findViewById(R.id.moodText)
        changeMoodBtn = findViewById(R.id.changeMoodBtn)
        instructionsBtn = findViewById(R.id.instructionsBtn)
        mainLayout = findViewById(R.id.mainLayout)

        val prefs: SharedPreferences = getSharedPreferences("MoodPrefs", MODE_PRIVATE)
        val mood = prefs.getString("mood", "No mood selected")
        moodText.text = "Your mood today: $mood"

        changeMoodBtn.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        instructionsBtn.setOnClickListener {
            startActivity(Intent(this, InstructionsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        val prefs = getSharedPreferences("MoodPrefs", MODE_PRIVATE)
        val mood = prefs.getString("mood", "No mood selected")

        val emoji = when (mood) {
            "Happy" -> "😊"
            "Neutral" -> "😐"
            "Stressed" -> "😫"
            "Tired" -> "😴"
            else -> ""
        }

        moodText.text = "Your mood today: $mood $emoji"

        when (mood) {
            "Happy" -> mainLayout.setBackgroundColor(getColor(R.color.happy_bg))
            "Neutral" -> mainLayout.setBackgroundColor(getColor(R.color.neutral_bg))
            "Stressed" -> mainLayout.setBackgroundColor(getColor(R.color.stressed_bg))
            "Tired" -> mainLayout.setBackgroundColor(getColor(R.color.tired_bg))
        }
    }
}
