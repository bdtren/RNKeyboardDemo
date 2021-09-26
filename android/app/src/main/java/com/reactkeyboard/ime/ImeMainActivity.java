package com.reactkeyboard.ime;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.reactkeyboard.R;

public class ImeMainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ime_main);

        Button btnInputSetting = findViewById(R.id.btnInputSetting);
        Button btnSetKeyboard = findViewById(R.id.btnSetKeyboard);
        btnInputSetting.setOnClickListener(l -> {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
        });

        btnSetKeyboard.setOnClickListener(l -> {
            InputMethodManager imeManager = (InputMethodManager)
                    getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
            imeManager.showInputMethodPicker();
        });

    }

}
