package com.example.yourdailyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UnplannedFoodActivity extends AppCompatActivity {
    private static final String[] FOODS =  {
            "pizza", "burger", "shwarma", "falafel", "donuts","cake"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unplanned_food);

        final Intent intent2 =getIntent();

        final EditText text = (EditText)findViewById(R.id.editText11);
        final EditText grams = (EditText)findViewById(R.id.editText12);
        final TextView cur =(TextView)findViewById(R.id.textView10);
        final TextView calories = (TextView)findViewById(R.id.textView9);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, FOODS);
        //Getting the instance of AutoCompleteTextView
        final AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.foodList);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);

        Button calc =(Button)findViewById(R.id.button11);
        Button add =(Button)findViewById(R.id.button12);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference(actv.getText().toString().toLowerCase()+"");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) { // sign in to the firebase and calculate the calories by the grams
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        Long value = dataSnapshot.getValue(Long.class);
                        cur.setText(value+"");
                        String b= grams.getText().toString().trim();
                        double res= (double) (((double)value/100.0)*Double.parseDouble(b));
                        int result = (int)res;
                        calories.setText(String.valueOf(result));



                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// button to go back to the main page and to add the calculated calories
                Intent intent =new Intent(getApplicationContext(),MainPage.class);

                int a= Integer.parseInt(intent2.getStringExtra("mycalories"));
                int b= Integer.parseInt(calories.getText().toString().trim());
                intent.putExtra("mycalories",Integer.toString(a+b));
                startActivity(intent);

            }
        });




    }
}
