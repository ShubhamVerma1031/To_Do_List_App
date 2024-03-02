package com.example.todolistapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var add: Button
    var dialog: AlertDialog? = null
    var layout: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add = findViewById(R.id.add)
        layout = findViewById(R.id.container)
        buildDialog()
        add.setOnClickListener(View.OnClickListener { dialog!!.show() })
    }

    fun buildDialog() {
        val builder = AlertDialog.Builder(this)
        val view: View = layoutInflater.inflate(R.layout.dialog, null)
        val name = view.findViewById<EditText>(R.id.nameEdit)
        builder.setView(view)
        builder.setTitle("Enter your Task")
            .setPositiveButton(
                "SAVE"
            ) { dialog, which -> addCard(name.text.toString()) }
            .setNegativeButton(
                "Cancel"
            ) { dialog, which -> }
        dialog = builder.create()
    }

    private fun addCard(name: String) {
        val view: View = layoutInflater.inflate(R.layout.card, null)
        val nameView = view.findViewById<TextView>(R.id.name)
        val delete = view.findViewById<Button>(R.id.delete)
        nameView.text = name
        delete.setOnClickListener { layout!!.removeView(view) }
        layout!!.addView(view)
    }
}