package com.example.createtable3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;




public class DocumentDetails extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_details);
        setTitle("New Document");
    }
/*
    public void AssignDocumentName(View view) {
        EditText x = findViewById(R.id.documentName);
        String documentName = x.getText().toString();
    }

    public void columnNames(View v){

        EditText documentNameField = findViewById(R.id.documentName);
        String documentNameInput = documentNameField.getText().toString();

        EditText column1 = findViewById(R.id.column1TextEnter);
        String column1Input = column1.getText().toString();

        EditText column2 = findViewById(R.id.column2TextEnter);
        String column2Input = column2.getText().toString();

        EditText column3 = findViewById(R.id.column3TextEnter);
        String column3Input = column3.getText().toString();

    }
*/
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        EditText editDocumentName = (EditText) findViewById(R.id.documentName);
        EditText editColumn1 = (EditText) findViewById(R.id.column1TextEnter);
        EditText editColumn2 = (EditText) findViewById(R.id.column2TextEnter);
        EditText editColumn3 = (EditText) findViewById(R.id.column3TextEnter);

        CharSequence documentNameData = editDocumentName.getText();
        outState.putCharSequence("DocumentNameSavedData", documentNameData);

        CharSequence writtenData1 = editColumn1.getText();
        outState.putCharSequence("Column1SavedData", writtenData1);

        CharSequence writtenData2 = editColumn2.getText();
        outState.putCharSequence("Column2MySavedData", writtenData2);

        CharSequence writtenData3 = editColumn3.getText();
        outState.putCharSequence("Column3MySavedData", writtenData3);


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        CharSequence storeDataDocumentName = savedInstanceState.getCharSequence("DocumentNameSavedData");
        EditText editTextDocumentName = (EditText)findViewById(R.id.documentName);
        editTextDocumentName.setText(storeDataDocumentName);

        CharSequence storeDataColumn1 = savedInstanceState.getCharSequence("Column1SavedData");
        EditText editTextColumn1 = (EditText)findViewById(R.id.column1TextEnter);
        editTextColumn1.setText(storeDataColumn1);

        CharSequence storeDataColumn2 = savedInstanceState.getCharSequence("Column2MySavedData");
        EditText editTextColumn2 = (EditText)findViewById(R.id.column2TextEnter);
        editTextColumn2.setText(storeDataColumn2);

        CharSequence storeDataColumn3 = savedInstanceState.getCharSequence("Column3MySavedData");
        EditText editTextColumn3 = (EditText)findViewById(R.id.column3TextEnter);
        editTextColumn3.setText(storeDataColumn3);

    }

    public void createDocument(View x){

        EditText column1Transfer = (EditText) findViewById(R.id.column1TextEnter);
        String column1Pass = column1Transfer.getText().toString();

        EditText column2Transfer = (EditText) findViewById(R.id.column2TextEnter);
        String column2Pass = column2Transfer.getText().toString();

        EditText column3Transfer = (EditText) findViewById(R.id.column3TextEnter);
        String column3Pass = column3Transfer.getText().toString();

        EditText documentNameTransfer = (EditText) findViewById(R.id.documentName);
        String documentNamePass = documentNameTransfer.getText().toString();


        Intent a = new Intent(this, DocumentInputs.class);
        a.putExtra("column1", column1Pass);
        a.putExtra("column2", column2Pass);
        a.putExtra("column3", column3Pass);
        a.putExtra("documentName", documentNamePass);
        startActivity(a);
    }
}


