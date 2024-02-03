import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const channel = "JDev.channel";
const keyNative = "showNativeView";

class NativeActivity extends StatelessWidget {
  static const platform = MethodChannel(channel);

  NativeActivity({Key? key}) : super(key: key) {
    platform.setMethodCallHandler(_handleMethod);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Native Activity"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: _showNativeView,
              child: const Text('Move to Native World!'),
            ),
          ],
        ),
      ),
    );
  }

  Future<void> _showNativeView() async {
    await platform.invokeMethod(keyNative);
  }

  Future<dynamic> _handleMethod(MethodCall call) async {
    log(call.toString());
    // switch (call.method) {
    //   case "message":
    //     return Future.value("");
    // }
  }
}
