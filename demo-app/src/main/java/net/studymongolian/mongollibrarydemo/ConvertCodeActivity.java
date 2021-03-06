package net.studymongolian.mongollibrarydemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.MongolFont;


public class ConvertCodeActivity extends AppCompatActivity {

    EditText etCodeWindow;
    MongolCode converter;
    //Typeface tfMongolFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_code);

        // initialize renderer
        converter = MongolCode.INSTANCE;

        etCodeWindow = (EditText) findViewById(R.id.etCodeWindow);
        etCodeWindow.setText("ᠮᠤᠩᠭᠤᠯ"); // Mongol
    }

    // Button click methods

    public void unicodeToMenksoftClick(View view) {
        hideKeyboard();

        String menksoftString = converter.unicodeToMenksoft(etCodeWindow.getText().toString());

        Typeface tf = MongolFont.get(MongolFont.QAGAN, this);
        etCodeWindow.setTypeface(tf);
        etCodeWindow.setText(menksoftString);
    }

    public void menksoftToUnicodeClick(View view) {
        hideKeyboard();

        String unicodeString = converter.menksoftToUnicode(etCodeWindow.getText().toString());
        etCodeWindow.setText(unicodeString);
        etCodeWindow.setTypeface(null, Typeface.NORMAL);
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public void copyClick(View view) {

        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(etCodeWindow.getText());
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Mongol", etCodeWindow.getText());
            clipboard.setPrimaryClip(clip);
        }
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public void pasteClick(View view) {

        CharSequence textToPaste = null;

        // get the text from the clipboard manager
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            try {
                textToPaste = clipboard.getText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard.getPrimaryClip() != null) {
                android.content.ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                textToPaste = item.getText();
            }
        }

        // insert the text at the cursor position, or if there is a selection it
        // replaces the selection with the text to paste
        if (textToPaste != null) {
            int start = Math.max(etCodeWindow.getSelectionStart(), 0);
            int end = Math.max(etCodeWindow.getSelectionEnd(), 0);
            etCodeWindow.getText().replace(Math.min(start, end), Math.max(start, end),
                    textToPaste, 0, textToPaste.length());
        }
    }

    public void clearClick(View view) {
        etCodeWindow.setText("");
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


}
