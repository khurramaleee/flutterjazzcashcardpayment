package com.example.fluttertestjazzcash;



import android.content.Intent;

import androidx.annotation.NonNull;

import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "com.flutter.khurramdev";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            final Map<String,String> arguments = call.arguments();
                            String Jazz_MerchantID = (String) arguments.get("Jazz_MerchantID");
                            String Jazz_Password = (String) arguments.get("Jazz_Password");
                            String Jazz_IntegritySalt = (String) arguments.get("Jazz_IntegritySalt");
                            String paymentReturnUrl = (String) arguments.get("paymentReturnUrl");
                            String price = (String) arguments.get("price");
                            if (call.method.equals("Print")) {
                                System.out.println(arguments);

                                Intent i = new Intent(MainActivity.this, PaymentActivity.class);

                                i.putExtra("price", price);
                                i.putExtra("paymentReturnUrl", paymentReturnUrl);
                                i.putExtra("Jazz_IntegritySalt", Jazz_IntegritySalt);
                                i.putExtra("Jazz_Password", Jazz_Password);
                                i.putExtra("Jazz_MerchantID", Jazz_MerchantID);


                                startActivityForResult(i, 0);
                            }
                        }
                );
    }
}