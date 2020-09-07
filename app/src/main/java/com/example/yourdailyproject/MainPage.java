package com.example.yourdailyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPage extends AppCompatActivity {
    Intent intent1,intent2,intent3,intent4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        final Button exit =(Button)findViewById(R.id.button5);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customers");
       final TextView a=(TextView)findViewById(R.id.textView3);
        final TextView b=(TextView)findViewById(R.id.textView4);
         Intent intent =getIntent();
        b.setText(intent.getStringExtra("mycalories"));

        intent1 = new Intent(getApplicationContext(),ProgramActivity.class);
        intent2 = new Intent(getApplicationContext(),ExerciseActivity.class);
        intent3 = new Intent(getApplicationContext(),UnplannedFoodActivity.class);
        intent4 = new Intent(getApplicationContext(),SettingsActivity.class);
        final Button bt3 = (Button)findViewById(R.id.button13);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // to open the settings activity
                intent4.putExtra("mycalories",b.getText().toString().trim());
                startActivity(intent4);


            }
        });


        final TextView c = (TextView)findViewById(R.id.textView6);

        final Button bt2 = (Button)findViewById(R.id.button10);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // to open the excercise activity
                intent3.putExtra("mycalories",b.getText().toString().trim());
                startActivity(intent3);

            }
        });
       final Button bt =(Button)findViewById(R.id.button6);
       bt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) { // to open the food program activity
               intent1.putExtra("mycalories",b.getText().toString().trim());
               startActivity(intent1);

           }
       });

       final Button bt1 =(Button)findViewById(R.id.button7);
       bt1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) { // to open the unplanned activity
                intent2.putExtra("mycalories",b.getText().toString().trim());
               startActivity(intent2);
           }
       });


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {// connect to firebase and get the value of our object(Customer)
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Customer value = dataSnapshot.getValue(Customer.class);
                a.setText("hello " +value.getFname()+" "+value.getLname());
                if(value.getTarget().equals("nautral"))
                c.setText("your EER is :"+(int)(354-(6.91*value.getAge())+1.4*(9.36*value.getWeight())+726*value.getHeight()));
                if(value.getTarget().equals("bulking"))
                    c.setText("your EER is :"+(int)((354-(6.91*value.getAge())+1.4*(9.36*value.getWeight())+726*value.getHeight())*1.2));
                if(value.getTarget().equals("cutting"))
                    c.setText("your EER is :"+(int)((354-(6.91*value.getAge())+1.4*(9.36*value.getWeight())+726*value.getHeight())*0.8));




            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
