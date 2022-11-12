package com.example.wk7_2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

private var written = false;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        var content = findViewById<TextView>(R.id.textview)

        val fn = "internal.txt"
        val fb = "File for testing "
        val fb2 = "New number written to file"
        var cnt = 0

        button.setOnClickListener {
            if (written == false) {
                val it = applicationContext.openFileOutput(fn, Context.MODE_PRIVATE)
                it.write((fb + " " + cnt.toString()).toByteArray())
                it.close()
                button.text = "READ"
                content.text = fb2
                cnt++
                written = true
            } else {
                applicationContext.openFileInput(fn).use {
                    var txt = it.bufferedReader().readLine()
                    Log.d("DKM", "---> $txt")
                    it.close()
                    content.text = txt
                }
                button.text = "WRITE"
                written = false
            }
        }
    }
}