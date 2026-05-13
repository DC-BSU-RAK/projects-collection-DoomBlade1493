package com.example.domainexpansiongenerator

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InstructionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modal_instructions)

        val closeBtn = findViewById<Button>(R.id.closeModal)
        closeBtn.setOnClickListener {
            finish()
        }
    }
}
