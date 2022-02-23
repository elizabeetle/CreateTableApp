package com.example.createtable3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * This class serves as the landing page for users.
 */
public class MainActivity extends AppCompatActivity {

/*TODO
-Implement another recyclerView to show users their previously created documents
-Settings button
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");


    }


    public void launchDocumentDetails(View x) {
           final FloatingActionButton button = findViewById(R.id.createNewDocument);
           Intent b = new Intent(this, DocumentDetails.class);
           startActivity(b);
    }




}