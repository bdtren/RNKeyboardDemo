package com.reactkeyboard.ime;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputConnection;

import com.reactkeyboard.R;
import com.reactkeyboard.ime.constaint.AppConstants;

public class MyInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    @Override
    public View onCreateInputView() {
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.number_pad);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();

        if (inputConnection != null) {
            if(String.valueOf(primaryCode).startsWith(String.valueOf(AppConstants.MENU_PREFIX_CODE)) ) {
                switch (primaryCode){
                    case AppConstants.MENU1:
                        inputConnection.commitText("Hello world!", 1);
                        break;
                    case AppConstants.MENU2:
                        openApp();
                        break;
                }
            } else {
                switch(primaryCode) {
                    case Keyboard.KEYCODE_DELETE :
                        CharSequence selectedText = inputConnection.getSelectedText(0);

                        if (TextUtils.isEmpty(selectedText)) {
                            inputConnection.deleteSurroundingText(1, 0);
                        } else {
                            inputConnection.commitText("", 1);
                        }

                        break;
                    default :
                        char code = (char) primaryCode;
                        inputConnection.commitText(String.valueOf(code), 1);
                }
            }

        }
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    private void openApp(){
        Intent launchIntent = null;

        try{
            launchIntent = getPackageManager().getLaunchIntentForPackage(AppConstants.applicationId);
        } catch (Exception ignored) {}

        if(launchIntent == null){
            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=" + AppConstants.applicationId)));
        } else {
            startActivity(launchIntent);
        }
    }
}
