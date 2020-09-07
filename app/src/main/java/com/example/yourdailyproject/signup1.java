package com.example.yourdailyproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.os.SystemClock.sleep;

public class signup1 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ProgressBar progressBar ;
     Customer customer;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);
        customer=new Customer();
        final EditText a =(EditText)findViewById(R.id.email);


        final EditText  b=(EditText)findViewById(R.id.password);

        final EditText fn =(EditText)findViewById(R.id.editText2);
        final EditText ln =(EditText)findViewById(R.id.editText4);
        final EditText pn =(EditText)findViewById(R.id.editText5);
        final RadioButton m = (RadioButton)findViewById(R.id.radioButton);
        final  RadioButton f = (RadioButton)findViewById(R.id.radioButton2);
        m.toggle();



        progressBar =(ProgressBar)findViewById(R.id.progrssbar);
        mAuth = FirebaseAuth.getInstance();

        Button bt =(Button)findViewById(R.id.button3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password =b.getText().toString().trim();
                final String email=a.getText().toString().trim();
                final String firstname = fn.getText().toString().trim();
                final  String lastname = ln.getText().toString().trim();
                final String phone = pn.getText().toString().trim();
                 intent =new Intent(getApplicationContext(),signup2.class);

            progressBar.setVisibility(View.VISIBLE);
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                a.setError("please enter a valid email address !");
                a.requestFocus();
                return;
            }
                if(firstname.isEmpty())
                {
                    fn.setError("please enter something");
                    fn.requestFocus();
                    return;

                }
                if(lastname.isEmpty())
                {
                    ln.setError("please enter something");
                    ln.requestFocus();
                    return;

                }

            if(email.isEmpty())
            {
                a.setError("please enter something");
                a.requestFocus();
                return;

            }
                if(password.isEmpty())
                {
                    b.setError("please enter something");
                    b.requestFocus();
                    return;

                }

                if(m.isChecked())
                {
                    intent.putExtra("gender","male");

                }
                else
                    intent.putExtra("gender","female");
                intent.putExtra("first",firstname);
                intent.putExtra("last",lastname);
                intent.putExtra("phone",phone);
                intent.putExtra("mail",email);

               mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       progressBar.setVisibility(View.GONE);
                       if(task.isSuccessful()) {
                           Toast.makeText(getApplicationContext(), "first stage complete !", Toast.LENGTH_SHORT).show();
                           sleep(1000);
                       }
                   else
                       {
                           Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                           return;

                       }
                       startActivity(intent);

                   }
               }
               );



            }
        });
    }
    private void next()
    {



    }
}
