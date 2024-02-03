package com.example.flutter_playground.flutter_playground

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.flutter_playground.flutter_playground.R.id.button
import io.flutter.Log as fl
import io.flutter.embedding.android.FlutterActivity

class SecondaryActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            fl.d("TAG", "pushed")
            Log.d("TAG", "pushed")

            button.text = "Clicked"

            // Puedes enviar el valor del botón como resultado a la actividad principal (Flutter)
            val data = "Valor del botón desde Kotlin nativo"
            val resultIntent = Intent()
            resultIntent.putExtra("buttonValue", data)
            setResult(Activity.RESULT_OK, resultIntent)

            // Cierra la actividad secundaria
            finish()
        }

    }
}
