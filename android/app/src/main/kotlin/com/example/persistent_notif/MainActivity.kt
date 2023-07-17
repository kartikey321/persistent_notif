package com.example.persistent_notif

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "your_channel_name")
            .setMethodCallHandler { call, result ->
                if (call.method == "showPermanentNotification") {
                    // Call the method to create a permanent notification
                    NotificationHandler.createPermanentNotification(this)
                } else {
                    result.notImplemented()
                }
            }
    }
}
