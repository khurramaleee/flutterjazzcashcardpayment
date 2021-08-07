package com.example.fluttertestjazzcash;



import android.content.Intent;

import androidx.annotation.NonNull;

import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "com.flutter.epic/epic";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            final double arguments = call.arguments();

                            if (call.method.equals("Print")) {
                                System.out.println(arguments);
                                if (arguments==20) {
                                    result.success("Yay");
                                }
                                else {
                                    result.success("Nay");
                                }
                                Intent i = new Intent(MainActivity.this, PaymentActivity.class);
                                //startActivity(new Intent(MainActivity.this, PaymentActivity.class));
                                i.putExtra("price", arguments);

                                startActivityForResult(i, 0);
                            }
                        }
                );
    }
}