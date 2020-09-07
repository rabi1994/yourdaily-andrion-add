package com.example.yourdailyproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        final RadioButton rb1 =(RadioButton)findViewById(R.id.cuttingbutton);
        final RadioButton rb2 =(RadioButton)findViewById(R.id.nautralbutton);
        final RadioButton rb3 =(RadioButton)findViewById(R.id.bulkingbutton);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customers");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customer customer =dataSnapshot.getValue(Customer.class);
                String a= customer.getTarget();
                if(a.equals("cutting"))
                    rb1.setChecked(true);
                if(a.equals("nautral"))
                    rb2.setChecked(true);
                if(a.equals("bulking"))
                    rb3.setChecked(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Button bt =(Button)findViewById(R.id.button16);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("customers");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customer customer =dataSnapshot.getValue(Customer.class);

                if(rb1.isChecked())
                    customer.setTarget("cutting");
                else

                if(rb2.isChecked())
                    customer.setTarget("nautral");
                else
                if(rb3.isChecked())
                    customer.setTarget("bulking");
                else {
                    Toast.makeText(getApplicationContext(), "nothing is checked !", Toast.LENGTH_SHORT).show();
                    return;
                }
                myRef.setValue(customer);
                Toast.makeText(getApplicationContext(),"updated !",Toast.LENGTH_SHORT);
                Intent intent =new Intent(getApplicationContext(),MainPage.class);
                intent.putExtra("mycalories","0");

                startActivity(intent);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



            }
        });


    }
}
