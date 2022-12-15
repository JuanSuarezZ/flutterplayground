package com.example.flutter_playground.flutter_playground

import android.content.Intent
import android.os.Bundle

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
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
                    startActivity(intent)
                    result.success(true)
                } else {
                    result.notImplemented()
                }
            }
        }
    }
}
