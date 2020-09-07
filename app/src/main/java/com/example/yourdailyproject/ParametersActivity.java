package com.example.yourdailyproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParametersActivity extends AppCompatActivity {
    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);
        final EditText at=(EditText)findViewById(R.id.agetext);
        final EditText wt=(EditText)findViewById(R.id.weighttext);
        final EditText ht =(EditText)findViewById(R.id.heighttext);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("customers");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 customer =dataSnapshot.getValue(Customer.class);

               at.setText(customer.getAge()+"");
               wt.setText(customer.getWeight()+"");
               ht.setText(customer.getHeight()+"");




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button bt =(Button)findViewById(R.id.updateButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer.setAge(Integer.parseInt(at.getText().toString().trim()));
                customer.setWeight(Double.parseDouble(wt.getText().toString().trim()));
                customer.setHeight(Double.parseDouble(ht.getText().toString().trim()));
                myRef.setValue(customer);
                Toast.makeText(getApplicationContext(),"updated !",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getApplicationContext(),MainPage.class);
                intent.putExtra("mycalories","0");
                startActivity(intent);

            }
        });

    }
}
