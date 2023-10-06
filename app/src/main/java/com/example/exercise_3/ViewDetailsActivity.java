// ViewDetailsActivity.java
package com.example.exercise_3;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewDetailsActivity extends AppCompatActivity {
    private ListView listView;
    private UserDataAdapter adapter;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        // Initialize your dbHelper here
        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);

        List<UserData> userList = new ArrayList<>();

        Cursor cursor = dbHelper.getAllRecords();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Extract data from the cursor for each record
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String dob = cursor.getString(cursor.getColumnIndex("dateOfBirth"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") String selectedImageTag = cursor.getString(cursor.getColumnIndex("selectedImageTag"));

                // Create a UserData object for this record
                UserData user = new UserData(name, email, dob, selectedImageTag);

                // Add the UserData object to the list
                userList.add(user);
            }
            cursor.close(); // Don't forget to close the cursor when you're done
        }


        adapter = new UserDataAdapter(this, R.layout.cardview_item, userList);
        listView.setAdapter(adapter);

        // Handle the back button click
        Button backButton = findViewById(R.id.backButton_Home);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // This will simulate the system's back button press
            }
        });
    }
}
