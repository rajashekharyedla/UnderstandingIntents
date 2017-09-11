package com.example.understandingintents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sashank on 03/09/17.
 */

public class ResultActivity extends Activity {
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        String inputString = extras.getString("yourKey");
        TextView textView = (TextView) findViewById(R.id.displayintentextra);
        textView.setText(inputString);
    }

    @Override
    public void finish(){
        EditText editText = (EditText) findViewById(R.id.returnValue);
        String text = editText.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("returnKey", text);
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
