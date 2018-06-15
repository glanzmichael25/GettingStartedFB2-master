package com.example.dog.gettingstartedfb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddToDataBase extends Activity
{
    private static final String TAG = "AddToDataBase";

    private Button mAddToDB;

    private EditText enterAFood;

    //add firebase database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_data_base);

        mAddToDB = (Button) findViewById(R.id.mAddToDb);
        enterAFood = (EditText) findViewById(R.id.enterAFood);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        mAddToDB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "onClick: Attempting to add object to database");
                String newFood = enterAFood.getText().toString();
                if(!newFood.equals(""))
                {
                    myRef.child("Food").child("Fav Food").child(newFood).setValue("true");
                    Toast.makeText(getApplicationContext(), "You Added:" + newFood, Toast.LENGTH_SHORT).show();
                    enterAFood.setText("");
                }
            }
        });


    }
}
