package com.example.lw.atry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by lenovo on 06/03/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mButtonSignUp;

    private ProgressDialog mProgressDialog;
    private FirebaseAuth mFirebaseAuth;

    private Button mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mProgressDialog = new ProgressDialog(this);

        mEmail = (EditText) findViewById(R.id.editTextEmail);
        mPassword = (EditText) findViewById(R.id.editTextPassword);
        mConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        mButtonSignUp = (Button) findViewById(R.id.buttonSignUp);

        mBack = (Button) findViewById(R.id.buttonBackSignUp);

        mButtonSignUp.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == mButtonSignUp){

            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String confirmPassword = mConfirmPassword.getText().toString().trim();

            if(validateUser(email, password, confirmPassword)){
                registerUser(email, password);
            }
        } else if (v == mBack) {
            finish();
            Intent intent = new Intent(LoginActivity.this, ChooseActivity.class);
            startActivity(intent);
        }
    }


    private boolean validateUser(String email, String password, String confirmPassword){
        boolean validate = false;

        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please input your email and password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please input your email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please input your password", Toast.LENGTH_SHORT).show();
        } else if(!confirmPassword.equals(password)){
            Toast.makeText(this, "Please input the right confirmation password", Toast.LENGTH_SHORT).show();
        } else if (password.length()<10){
            Toast.makeText(this, "Password should be longer than 9 characters", Toast.LENGTH_SHORT).show();
        } else {
            validate = true;
        }

        return validate;
    }

    private void registerUser(String email, String password){
        mProgressDialog.setMessage("Registering User...");
        mProgressDialog.show();

        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Could not register... please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
