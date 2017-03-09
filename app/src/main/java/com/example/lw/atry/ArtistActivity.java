package com.example.lw.atry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by lenovo on 10/03/2017.
 */

public class ArtistActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mArtist;
    private Spinner mGenre;
    private Button mInput;

    private DatabaseReference mDatabaseReference;

    private Button mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("artists");

        mArtist = (EditText) findViewById(R.id.editTextArtist);
        mGenre = (Spinner) findViewById(R.id.spinnerGenre);
        mInput = (Button) findViewById(R.id.buttonInput);

        mBack = (Button) findViewById(R.id.buttonBackSaveData);

        mInput.setOnClickListener(this);
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String artistName = mArtist.getText().toString().trim();
        String artistGenre = mGenre.getSelectedItem().toString();

        if (v == mInput){
            if (validate(artistName)){
                addArtist(artistName, artistGenre);
                Toast.makeText(this, "Artist added succesfully", Toast.LENGTH_SHORT).show();
                mArtist.setText("");
                mGenre.setSelection(0);
            } else {
                Toast.makeText(this, "Please type your artist", Toast.LENGTH_SHORT).show();
            }
        } else if (v == mBack){
            finish();
            Intent intent = new Intent(ArtistActivity.this, ChooseActivity.class);
            startActivity(intent);
        }
    }

    private boolean validate(String artistName){
        boolean validate = false;
        if (!TextUtils.isEmpty(artistName)){
            validate = true;
        }
        return validate;
    }

    private void addArtist(String artistName, String artistGenre){
        String artistId = mDatabaseReference.push().getKey();

        ArtistModel artistModel = new ArtistModel(artistId, artistName, artistGenre);
        mDatabaseReference.child(artistId).setValue(artistModel);
    }
}
