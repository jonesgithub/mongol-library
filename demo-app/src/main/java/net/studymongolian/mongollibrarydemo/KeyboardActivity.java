package net.studymongolian.mongollibrarydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import net.studymongolian.mongollibrary.ImeContainer;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.KeyboardAeiou;
import net.studymongolian.mongollibrary.KeyboardQwerty;
import net.studymongolian.mongollibrary.MongolEditText;
import net.studymongolian.mongollibrary.MongolInputMethodManager;


public class KeyboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        EditText editText = findViewById(R.id.edittext);
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);

        // Uncomment this if you want to style the keyboard

//        // keyboards style
//        Keyboard.StyleBuilder keyboardStyle = new Keyboard.StyleBuilder();
//        keyboardStyle.setKeyBackgroundColor(Color.BLUE)
//                .setKeyPressedColor(Color.RED)
//                .setKeyBorderColor(Color.BLACK)
//                .setKeyBorderRadius(40)
//                .setKeyBorderWidth(2)
//                .setPopupBackgroundColor(Color.BLACK)
//                .setPopupTextColor(Color.WHITE)
//                .setPopupHighlightColor(Color.BLUE)
//                .setKeyPrimaryTextColor(Color.WHITE)
//                .setKeySecondaryTextColor(Color.GRAY)
//                .setKeyImageTheme(KeyImage.Theme.DARK) // for a light image
//                //TODO .setKeyImageColorFilter(Color.BLUE) or (Color.BLUE, PorterDuffMode)
//                .setKeySpacing(10);
//
//        // init keyboards with styles
//        Keyboard aeiou = new KeyboardAeiou(this, keyboardStyle);
//        Keyboard qwerty = new KeyboardQwerty(this, keyboardStyle);
//        Keyboard custom = new CustomKeyboard(this, keyboardStyle);

        // keyboards to include (default style)
        Keyboard aeiou = new KeyboardAeiou(this);
        Keyboard qwerty = new KeyboardQwerty(this);
        Keyboard custom = new CustomKeyboard(this);

        // add keyboards to the IME container
        ImeContainer imeContainer = findViewById(R.id.keyboard);
        imeContainer.addKeyboard(aeiou); // first one is the default
        imeContainer.addKeyboard(qwerty);
        imeContainer.addKeyboard(custom);

        // The MongolInputMethodManager handles communication between the keyboards and
        // the MongolEditText (or EditText).
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(editText);
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.NO_EDITORS);
        mimm.startInput();
    }
}
