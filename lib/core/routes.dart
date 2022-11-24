import 'package:flutter/material.dart';
import 'package:flutter_playground/pages/3d/drawer3d.dart';

Map<String, Widget Function(BuildContext)> getAplicationRoutes = {
  '3dImagesExample': (_) => const Drawer3D(),
};
