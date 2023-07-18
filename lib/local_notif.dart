import 'package:flutter/services.dart';

class NotificationChannel {
  static const MethodChannel _channel =
      const MethodChannel('com.example.persistent_notif/channel');

  Future<void> showCustomNotification() async {
    try {
      await _channel.invokeMethod('showPermanentNotification');
    } catch (e) {
      print('Failed to show custom notification: $e');
    }
  }
}
