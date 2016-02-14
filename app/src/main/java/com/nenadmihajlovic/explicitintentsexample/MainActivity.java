package com.nenadmihajlovic.explicitintentsexample;
/**
 * EXPLICIT INTENTS EXAMPLE - MainActivity
 * Created by Sillass on 12.2.2016..
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //variables for startActivityForResult - 1. example:
    private static final int REQUEST_CODE_1 = 1; //request code for startActivityForResult
    private TextView mResultTextView;
    private Button mForResultButton; //startActivityForResult button
    private String mAnswer1;

    //variables for send data to another activity - 2. example:
    public static final String SENDING_KEY = "com.nenadmihajlovic.explicitintentsexample.sending";
    private EditText mDataForSendEditText;
    private Button mFirstWayButton; //send data to another activity button - first way
    private Button mSecondWayButton; //send data to another activity button - second way

    //variables for two-way communication  - 3. example;
    private static final int REQUEST_CODE_3 = 3; //request code for startActivityForResult
    private EditText mTwoWayEditText;
    private Button mTwoWayButton;
    private String mAnswer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //widgets and Intent for startActivityForResult (1. example)
        mResultTextView = (TextView) findViewById(R.id.result_textView);
        mForResultButton = (Button) findViewById(R.id.start_activity_for_result_button);
        mForResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent for startActivityForResult
                Intent i = new Intent(MainActivity.this, FirstActivity.class);
                startActivityForResult(i, REQUEST_CODE_1);
            }
        });

        //widgets and Intent for send data to another activity (2. example)
        mDataForSendEditText = (EditText) findViewById(R.id.send_data_editText);
        //First way (2. example)
        mFirstWayButton = (Button) findViewById(R.id.send_data_button);
        mFirstWayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra(SENDING_KEY, mDataForSendEditText.getText().toString());
                startActivity(i);
            }
        });
        //Second way (2. example)
        mSecondWayButton = (Button) findViewById(R.id.send_data_button_2);
        mSecondWayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = SecondActivity.newIntent
                        (MainActivity.this, mDataForSendEditText.getText().toString());
                startActivity(i);
            }
        });

        //widgets and Intent for two-way communication (3. example)
        mTwoWayEditText = (EditText) findViewById(R.id.two_way_editText);
        mTwoWayButton = (Button) findViewById(R.id.two_way_button);
        mTwoWayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forSendString = mTwoWayEditText.getText().toString();
                Intent i = ThirdActivity.newIntent(MainActivity.this, forSendString);
                startActivityForResult(i, REQUEST_CODE_3);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //for 1. and 3. example
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        // for 1. example
        if (requestCode == REQUEST_CODE_1) {
            if (data == null) {
                return;
            }
            mAnswer1 = "Result is: " + FirstActivity.resultIs(data);
        }
        // for 3. example
        if (requestCode==REQUEST_CODE_3) {
            if (data==null) {
                return;
            }
            mAnswer3 ="Result is: " + ThirdActivity.resultIs(data);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //for 1. example
        if (mAnswer1 != null) {
            mResultTextView.setText(mAnswer1);
        }
        //for 3. example
        if (mAnswer3 !=null) {
            mTwoWayEditText.setText(mAnswer3);
        }
    }
}