package com.example.flutter_playground.flutter_playground

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {

    companion object {
        const val CHANNEL = "JDev.channel";
        const val KEY_NATIVE = "showNativeView";
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(FlutterEngine(applicationContext))

        flutterEngine?.dartExecutor?.let {
            MethodChannel(it.getBinaryMessenger(), CHANNEL).setMethodCallHandler { call, result ->
                if (call.method == KEY_NATIVE) {
                    val intent = Intent(this, SecondaryActivity::class.java)

                    // Inicia la actividad secundaria con startActivityForResult
                    startActivityForResult(intent, 1)

                    // No necesitas llamar result.success(res) aquí
                    // El resultado se manejará en onActivityResult
                } else {
                    result.notImplemented()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) { // Ajusta el requestCode según sea necesario
            if (resultCode == Activity.RESULT_OK) {
                val buttonValue = data?.getStringExtra("buttonValue")
                if (buttonValue != null) {
                    val binaryMessenger = flutterEngine?.dartExecutor?.binaryMessenger
                    if (binaryMessenger != null) {
                        val channel = MethodChannel(binaryMessenger, "JDev.channel")
                        channel.invokeMethod("valorRetornadoDesdeKotlin", buttonValue)
                    }
                }
            }
        }
    }



}
