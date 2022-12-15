import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const CHANNEL = "JDev.channel";
const KEY_NATIVE = "showNativeView";

class NativeActivity extends StatelessWidget {
  static const platform = MethodChannel(CHANNEL);

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

  Future<Null> _showNativeView() async {
    await platform.invokeMethod(KEY_NATIVE);
  }

  Future<dynamic> _handleMethod(MethodCall call) async {
    switch (call.method) {
      case "message":
        debugPrint(call.arguments);
        return Future.value("");
    }
  }
}
