package com.example.yourdailyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
public class signup2 extends AppCompatActivity {
    Customer customer;
    DatabaseReference data;
    private static final String[] COUNTRIES = new String[]{
            "Cutting ", "Nautral ", "Bulking " };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        final RadioButton cutting =(RadioButton)findViewById(R.id.radioButton3);
        final RadioButton nautral =(RadioButton)findViewById(R.id.nautralbutton);
        final RadioButton bulking= (RadioButton)findViewById(R.id.bulkingbutton);




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("customers");
        final Intent intent=getIntent();
        final Customer customer =new Customer();
        final EditText age1 =(EditText)findViewById(R.id.editText8);
        final EditText weight1 =(EditText)findViewById(R.id.editText9);
         final EditText height1 =(EditText)findViewById(R.id.editText10);
        Button a =(Button)findViewById(R.id.button4);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String age = age1.getText().toString().trim();
                String weight = weight1.getText().toString().trim();
                String height = height1.getText().toString().trim();
                customer.setFname(intent.getStringExtra("first"));
                customer.setLname(intent.getStringExtra("last"));
                String gender = intent.getStringExtra("gender");
                if(cutting.isChecked())
                    customer.setTarget("cutting");
                if(nautral.isChecked())
                    customer.setTarget("nautral");
                if(bulking.isChecked())
                    customer.setTarget("bulking");
                if(gender.equals("male"))
                {
                    customer.setGender(Gender.male);
                }
                else
                    customer.setGender(Gender.female);
                customer.setPhone(intent.getStringExtra("phone"));
                customer.setAge(Integer.parseInt((age)));
                customer.setWeight(Double.parseDouble(weight));
                customer.setHeight(Double.parseDouble(height));
                customer.setMail(intent.getStringExtra("mail"));


                myRef.setValue(customer);
                Toast.makeText(getApplicationContext(),"succeccfull registered ",Toast.LENGTH_LONG).show();
                active();

            }
        });
    }
    private void active()
    {
        Intent intent= new Intent(this,MainPage.class);
        intent.putExtra("mycalories","0");

        startActivity(intent);
    }
}
