package com.example.understandingintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        EditText editText = (EditText) findViewById(R.id.inputforintent);
        String text = editText.getText().toString();

        /*Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("yourKey", text);
        startActivityForResult(i, REQUEST_CODE);*/

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        startActivity(sharingIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if(intent.hasExtra("returnKey")){
                String result = intent.getExtras().getString("returnKey");
                if(result != null && result.length() > 0){
                    Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void imagePick(View view){
        Intent intent = new Intent(this, ImagePickAcitvity.class);
        startActivity(intent);

    }
}
