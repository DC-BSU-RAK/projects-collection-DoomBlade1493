package com.example.domainexpansiongenerator

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    private var selectedMood: String? = null
    private var selectedTheme: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moodColumn = findViewById<LinearLayout>(R.id.moodColumn)
        val themeColumn = findViewById<LinearLayout>(R.id.themeColumn)
        val outputText = findViewById<TextView>(R.id.outputText)
        val generateBtn = findViewById<Button>(R.id.generateBtn)
        val infoBtn = findViewById<Button>(R.id.infoBtn)

        infoBtn.setOnClickListener {
            startActivity(Intent(this, InstructionsActivity::class.java))
        }

        val moods = listOf("Happy", "Angry", "Calm", "Sad", "Anxious", "Confident")
        val themes = listOf("Chaos", "Silence", "Growth", "Ruin", "Time", "Memory", "Fate")

        moods.forEach { mood ->
            val tv = TextView(this)
            tv.text = mood
            tv.textSize = 20f
            tv.setPadding(20, 20, 20, 20)
            tv.setTextColor(ContextCompat.getColor(this, R.color.cursed_light))

            tv.setOnClickListener {
                selectedMood = mood
                highlightSelection(moodColumn, tv)
            }

            moodColumn.addView(tv)
        }

        themes.forEach { theme ->
            val tv = TextView(this)
            tv.text = theme
            tv.textSize = 20f
            tv.setPadding(20, 20, 20, 20)
            tv.setTextColor(ContextCompat.getColor(this, R.color.cursed_light))

            tv.setOnClickListener {
                selectedTheme = theme
                highlightSelection(themeColumn, tv)
            }

            themeColumn.addView(tv)
        }

        generateBtn.setOnClickListener {
            if (selectedMood != null && selectedTheme != null) {
                outputText.text = generateDomain(selectedMood!!, selectedTheme!!)
            } else {
                outputText.text = getString(R.string.select_mood_theme)
            }
        }
    }

    private fun highlightSelection(parent: LinearLayout, selectedView: TextView) {
        for (i in 0 until parent.childCount) {
            parent.getChildAt(i).setBackgroundColor(
                ContextCompat.getColor(this, android.R.color.transparent)
            )
        }

        selectedView.setBackgroundColor(
            ContextCompat.getColor(this, R.color.cursed_glow)
        )
    }

    private fun generateDomain(mood: String, theme: String): String {
        val domainName = when (theme) {
            "Chaos" -> "$mood Tempest of Cursed Chaos"
            "Silence" -> "$mood Garden of Silent Stillness"
            "Growth" -> "$mood Sanctuary of Endless Bloom"
            "Ruin" -> "$mood Cathedral of Crimson Ruin"
            "Time" -> "$mood Clockwork Realm of Frozen Time"
            "Memory" -> "$mood Archive of Fading Echoes"
            "Fate" -> "$mood Crown of Unbroken Fate"
            else -> "Unnamed Domain"
        }

        return "Domain Expansion: $domainName\n\n" +
                "A cursed realm shaped by your emotional state and the ancient theme of $theme. " +
                "Within this domain, your cursed energy manifests with overwhelming authority."
    }
}
