package com.ssh.test.voicerecognition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final int REQUEST_OK = 1;
    private static final int RESULT_OK = -1;

    private static final String TAG = "MainActivity";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        findViewById(R.id.button1).setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        String language = "de";
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, language);
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, true);

        try {

            startActivityForResult(intent, REQUEST_OK);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(this, "Error initializing speech to text engine: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);

        Log.d(TAG, "requestCode: " + requestCode + " resultCode: " + resultCode);

        if (requestCode == REQUEST_OK && resultCode == RESULT_OK) {
            ArrayList<String> speech = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Log.d(TAG, Integer.toString(speech.size()));

            ((TextView) findViewById(R.id.text1)).setText(speech.get(0));

            for (String spoken : speech) {
                Log.d(TAG, spoken);

            }


        } else {
            ((TextView) findViewById(R.id.text1)).setText("no result");

        }

    }
}
