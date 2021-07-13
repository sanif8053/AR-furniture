package com.example.foodcenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.carhub.Model.User;
import com.example.foodcenter.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class signUp extends AppCompatActivity {

    final Pattern NAME_PATTERN = Pattern.compile("[^\\s][a-zA-Z\\s]+", Pattern.CASE_INSENSITIVE);

    final Pattern PHONE_PATTERN = Pattern.compile("^((\\+92)|(0092)|(03))\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$");

    EditText name,phoneNummber,email,password,Age;

    TextView Already;

    Button register;

    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference table_user=database.getReference("foodUser");

    final FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name=findViewById(R.id.edit_name);

        phoneNummber=findViewById(R.id.edit_phone);

        email=findViewById(R.id.edit_email);

        password=findViewById(R.id.edit_password);
        Age=findViewById(R.id.edit_age);
       // confirmpassword=findViewById(R.id.edit_confirmpassword);

        register=findViewById(R.id.signup);

        Already=findViewById(R.id.tv_backsignIn);

        Already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signUp.this,login.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")){
                    name.setError("Enter your name");
                    name.requestFocus();

                }else if (name.getText().toString().length()<5){
                    name.setError("Name too Short");
                    name.requestFocus();

                }else if (!NAME_PATTERN.matcher(name.getText().toString()).matches()){
                    name.setError("Enter correct name");
                    name.requestFocus();

                }else if (phoneNummber.getText().toString().equals("")){
                    phoneNummber.setError("Please enter valid phone no");
                    phoneNummber.requestFocus();

                }else if (phoneNummber.getText().toString().length()<11){
                    phoneNummber.setError(" phone no must be 11 digits");
                    phoneNummber.requestFocus();

                }else if (!PHONE_PATTERN.matcher(phoneNummber.getText().toString()).matches()){
                    phoneNummber.setError("Enter correct phone no");
                    phoneNummber.requestFocus();

                }else if (email.getText().toString().equals("")){
                    email.setError("Please Enter Email Address");
                    email.requestFocus();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError("Enter a valid email address");
                    email.requestFocus();
                }else if (password.getText().toString().equals("")){
                    password.setError("Enter the password");
                    password.requestFocus();
                }else if (password.getText().toString().length()<6) {
                    password.setError("password too weak");
                    password.requestFocus();
//                } else  if (confirmpassword.getText().toString().equals("")){
//                    confirmpassword.setError("Please Enter same password");
//                    confirmpassword.requestFocus();
                }else {
                    registerUser(email.getText().toString(),password.getText().toString());
                }
            }
        });
    }

    public void registerUser(final String edit_email,String edit_password){

        final ProgressDialog progressDialog=new ProgressDialog(signUp.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        mFirebaseAuth.createUserWithEmailAndPassword(edit_email,edit_password).addOnCompleteListener(signUp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(name.getText().toString(),phoneNummber.getText().toString(),email.getText().toString(),password.getText().toString());
                    FirebaseDatabase.getInstance().getReference("foodUser").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(signUp.this, "Error Loading user data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    startActivity(new Intent(signUp.this,login.class));
                }else {
                    Toast.makeText(signUp.this, "SignUp Failed "+task.getException().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
