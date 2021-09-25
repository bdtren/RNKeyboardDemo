package com.reactkeyboard.ime;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNImeModule extends ReactContextBaseJavaModule {
    RNImeModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "IME";
    }

    @ReactMethod
    public void startNativeActivity() {
        Activity currActivity = getCurrentActivity();
        Intent intent = new Intent(currActivity, ImeMainActivity.class);

        currActivity.startActivity(intent);
    }
}
