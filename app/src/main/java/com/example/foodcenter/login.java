package com.example.foodcenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    EditText email,password;

    Button signin;

    TextView createAcc,forgot_pass;

    FirebaseDatabase database;

    DatabaseReference table_user;

    FirebaseAuth.AuthStateListener mFirbaseAuthState;

    FirebaseAuth mFirbaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.edit_email);

        password=findViewById(R.id.edit_password);

        mFirbaseAuth = FirebaseAuth.getInstance();

        mFirbaseAuthState= new FirebaseAuth.AuthStateListener() {

            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser=mFirbaseAuth.getCurrentUser();

              if (mFirebaseUser != null){

                  //Intent inte=new Intent(login.this,home.class);

                  //inte.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                  //startActivity(inte);

                 // Toast.makeText(login.this, "You are login in ", Toast.LENGTH_SHORT).show();

              }
            }
        };





        signin = findViewById(R.id.userlogin);

        createAcc=findViewById(R.id.create_account);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent= new Intent(login.this,signUp.class);
                startActivity(intent);

            }
        });

        database=FirebaseDatabase.getInstance();
        table_user=database.getReference("foodUser");

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().equals("")){
                    email.setError("Enter your email");
                    email.requestFocus();
                }else if (password.getText().toString().equals("")){
                    password.setError("Enter your password");
                    password.requestFocus();
                }else {
                    loginAuthentication(email.getText().toString(),password.getText().toString());
                }
            }
        });
    }

    private void loginAuthentication(String email,String password){

        mFirbaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Intent intent1=new Intent(login.this,home.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent1);
                }else {
                    Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void showForgotPassDialog(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        alertDialog.setTitle("Forgot Password");

        alertDialog.setMessage("Enter your email");

        LayoutInflater inflater=this.getLayoutInflater();

        final Context context=inflater.getContext();

        final LayoutInflater inflater1=LayoutInflater.from(context);

        View forgot_view= inflater1.inflate(R.layout.forgot_pass,null);



    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirbaseAuth.addAuthStateListener(mFirbaseAuthState);
    }

}
