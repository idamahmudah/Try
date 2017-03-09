package com.example.lw.atry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lenovo on 10/03/2017.
 */

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonSignUp;
    private Button mButtonSaveData;

    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        mButtonSignUp = (Button) findViewById(R.id.buttonSignUpActivity);
        mButtonSaveData = (Button) findViewById(R.id.buttonSaveDataActivity);

        mButtonSignUp.setOnClickListener(this);
        mButtonSaveData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mButtonSignUp){
            mIntent = new Intent(ChooseActivity.this, LoginActivity.class);
        } else if (v == mButtonSaveData){
            mIntent = new Intent(ChooseActivity.this, ArtistActivity.class);
        }
        startActivity(mIntent);
    }
}
