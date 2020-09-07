package com.example.yourdailyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
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

public class ExerciseActivity extends AppCompatActivity {
    private static final String[] EXERSICE =  {
            "running", "strength", "bicycle", "ski",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        final TextView textView =(TextView)findViewById(R.id.textView7);
        final EditText editText =(EditText)findViewById(R.id.editText6);
        final EditText editText1 =(EditText)findViewById(R.id.editText7);
        final TextView textView1 =(TextView)findViewById(R.id.textView8);
        final TextView textView2 =(TextView)findViewById(R.id.textView4);
        final Intent intent =getIntent();
        final String res=intent.getStringExtra("mycalories");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, EXERSICE);
        //Getting the instance of AutoCompleteTextView
        final AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.Exlist);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);

        final  Button bt =(Button)findViewById(R.id.button9);






        final Button button =(Button)findViewById(R.id.button8) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference(actv.getText().toString().toLowerCase()+"");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                textView.setText(value+"");
                String a= (String) textView.getText();
                String b= editText1.getText().toString().trim();
                textView1.setText( String.valueOf(Integer.parseInt(a)*Integer.parseInt(b)));
                bt.setClickable(true);



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });

            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a= Integer.parseInt(intent.getStringExtra("mycalories"));
                int b= Integer.parseInt(textView1.getText().toString().trim());
                Intent intent1 =new Intent(getApplicationContext(),MainPage.class);
                intent1.putExtra("mycalories",Integer.toString(a-b));
                startActivity(intent1);
            }
        });
    }
}
