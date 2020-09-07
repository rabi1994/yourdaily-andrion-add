package com.example.yourdailyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

import static android.R.layout.simple_list_item_1;

public class ProgramActivity extends AppCompatActivity {
    public static String[] myarr = new String[9];


    ListView listView;
    ArrayAdapter adapter;
    private  static  String breakfast ="breakfast";
    private  static  String breakfastprotein ="breakfastprotein";
    private  static  String breakfastrest ="breakfastrest";
    private  static  String dinner ="dinner";
    private  static  String dinnerprotein ="dinnerprotein";
    private  static  String dinnerrest ="dinnerrest";
    private  static  String dinner2 ="dinner2";
    private  static  String dinner2protein ="dinner2protein";
    private  static  String dinner2rest ="dinner2rest";






    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customers");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Customer value = dataSnapshot.getValue(Customer.class);
                double allcalories = (354 - (6.91 * value.getAge()) + 1.4 * (9.36 * value.getWeight()) + 726 * value.getHeight());
                if (value.getTarget().equals("cutting"))
                    allcalories *= 0.85;
                if (value.getTarget().equals("bulking"))
                    allcalories *= 1.15;

                Random rnd = new Random();
                String[] myarr = new String[9];
                Cottage cottage = new Cottage();
                FromageBlanc fromageBlanc = new FromageBlanc();
                ProteinShake proteinshake = new ProteinShake();
                ChickenBreast chickenbreast = new ChickenBreast();
                Fish fish = new Fish();
                Tuna tuna = new Tuna();
                RedMeat redmeat = new RedMeat();
                Rice rice = new Rice();
                Pasta pasta = new Pasta();
                Potato potato = new Potato();
                SweetPotato sweetpotato = new SweetPotato();


                BrownBread brownbread = new BrownBread();
                Oatmeal oatmeal = new Oatmeal();
                Food myprotein = null;
                int a;
                Food rest = null;
                boolean flag = true;


                a = rnd.nextInt(3) + 1;
                if (a == 1)
                    myprotein = cottage;
                if (a == 2)
                    myprotein = fromageBlanc;
                if (a == 3)
                    myprotein = proteinshake;
                a = rnd.nextInt(2) + 1;
                if (a == 1)
                    rest = brownbread;
                else
                    rest = oatmeal;


                int c = 0;
                int count = 0;

                double calories = (double) allcalories * 0.2;
                double proteinMax = calories * 0.4;
                double restcalories = calories * 0.6;

                while (proteinMax > 5) {
                    c++;
                    proteinMax -= myprotein.getCalories();
                }
                while (restcalories > 5) {
                    count++;
                    restcalories -= rest.getCalories();

                }

                if(savedInstanceState!=null) {
                    myarr[0]=savedInstanceState.getString(breakfast);
                    myarr[1] = savedInstanceState.getString(breakfastprotein);
                    myarr[2] = savedInstanceState.getString(breakfastrest);


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Your Daily program",Toast.LENGTH_SHORT).show();
                    myarr[0] = "Breakfast";
                    myarr[1] = myprotein + "\t" + c * 10 + " gram";
                    myarr[2] = rest + "\t" + count * 10 + " gram";
                }





                myprotein = null;

                rest = null;
                flag = true;


                a = rnd.nextInt(4) + 1;
                if (a == 1)
                    myprotein = chickenbreast;
                if (a == 2)
                    myprotein = fish;
                if (a == 3)
                    myprotein = redmeat;
                if (a == 4)
                    myprotein = tuna;
                a = rnd.nextInt(4) + 1;
                if (a == 1)
                    rest = rice;
                if (a == 2)
                    rest = pasta;
                if (a == 3)
                    rest = potato;
                if (a == 4)
                    rest = sweetpotato;


                c = 0;
                count = 0;

                calories = (double) allcalories * 0.4;
                proteinMax = calories * 0.4;
                restcalories = calories * 0.6;

                while (proteinMax > 5) {
                    c++;
                    proteinMax -= myprotein.getCalories();
                }
                while (restcalories > 5) {
                    count++;
                    restcalories -= rest.getCalories();

                }

                if(savedInstanceState!=null) {
                    myarr[3] =savedInstanceState.getString(dinner);
                    myarr[4] = savedInstanceState.getString(dinnerprotein);
                    myarr[5] = savedInstanceState.getString(dinnerrest);

                }
                else
                {
                    myarr[3] = "dinner";
                    myarr[4] = myprotein + "\t" + c * 10 + " gram";
                    myarr[5] = rest + "\t" + count * 10 + " gram";
                }

                myprotein = null;

                rest = null;
                flag = true;


                a = rnd.nextInt(4) + 1;
                if (a == 1)
                    myprotein = chickenbreast;
                if (a == 2)
                    myprotein = fish;
                if (a == 3)
                    myprotein = redmeat;
                if (a == 4)
                    myprotein = tuna;
                a = rnd.nextInt(4) + 1;
                if (a == 1)
                    rest = rice;
                if (a == 2)
                    rest = pasta;
                if (a == 3)
                    rest = potato;
                if (a == 4)
                    rest = sweetpotato;


                c = 0;
                count = 0;

                calories = (double) allcalories * 0.4;
                proteinMax = calories * 0.4;
                restcalories = calories * 0.6;

                while (proteinMax > 5) {
                    c++;
                    proteinMax -= myprotein.getCalories();
                }
                while (restcalories > 5) {
                    count++;
                    restcalories -= rest.getCalories();

                }

                if(savedInstanceState!=null) {
                    myarr[6] = savedInstanceState.getString(dinner2);
                    myarr[7] = savedInstanceState.getString(dinner2protein);
                    myarr[8] = savedInstanceState.getString(dinner2rest);

                }
                else
                {
                    myarr[6] = "night dinner";
                    myarr[7] = myprotein + "\t" + c * 10 + " gram";
                    myarr[8] = rest + "\t" + count * 10 + " gram";
                }
                 listView = (ListView) findViewById(R.id.list_view);

                 adapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.activity_program, myarr);
                adapter = new ArrayAdapter<String>(getApplicationContext(),
                        simple_list_item_1, new ArrayList<String>());
                adapter.add(myarr[0]);
                adapter.add(myarr[1]);

                adapter.add(myarr[2]);

                adapter.add(myarr[3]);

                adapter.add(myarr[4]);
                adapter.add(myarr[5]);

                adapter.add(myarr[6]);
                adapter.add(myarr[7]);
                adapter.add(myarr[8]);
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    
    protected void onResume(Bundle savedInstanceState) {


        super.onResume();
        myarr[0]=savedInstanceState.getString(breakfast);
        myarr[1]=savedInstanceState.getString(breakfastprotein);
        myarr[2]=savedInstanceState.getString(breakfastrest);
        myarr[3]=savedInstanceState.getString(dinner);
        myarr[4]=savedInstanceState.getString(dinnerprotein);
        myarr[5]=savedInstanceState.getString(dinnerrest);
        myarr[6]=savedInstanceState.getString(dinner2);
        myarr[7]=savedInstanceState.getString(dinner2protein);
        myarr[8]=savedInstanceState.getString(dinner2rest);

        listView = (ListView) findViewById(R.id.list_view);

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.activity_program, myarr);
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                simple_list_item_1, new ArrayList<String>());
        adapter.add(myarr[0]);
        adapter.add(myarr[1]);

        adapter.add(myarr[2]);

        adapter.add(myarr[3]);

        adapter.add(myarr[4]);
        adapter.add(myarr[5]);

        adapter.add(myarr[6]);
        adapter.add(myarr[7]);
        adapter.add(myarr[8]);
        listView.setAdapter(adapter);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString(breakfast,myarr[0]);
        savedInstanceState.putString(breakfastprotein,myarr[1]);
        savedInstanceState.putString(breakfastrest,myarr[2]);
        savedInstanceState.putString(dinner,myarr[3]);
        savedInstanceState.putString(dinnerprotein,myarr[4]);
        savedInstanceState.putString(dinnerrest,myarr[5]);
        savedInstanceState.putString(dinner2,myarr[6]);
        savedInstanceState.putString(dinner2protein,myarr[7]);
        savedInstanceState.putString(dinner2rest,myarr[8]);




        super.onSaveInstanceState(savedInstanceState);



    }

}