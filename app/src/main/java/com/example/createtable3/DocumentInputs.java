package com.example.createtable3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Serves as link between users' inputs and the PDF document.
 * This class handles all data related to the finished PDF document.
 */

public class DocumentInputs extends Activity implements RemoveClickListner{
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button btnAddItem;
    Button btnCreateDocument;
    ArrayList<RecyclerData> myList = new ArrayList<>();
    EditText etColumn1, etColumn2, etColumn3;
    String word = "",definition = "", sentence = "";
    ImageView crossImage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_inputs);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerAdapter = new RecyclerAdapter(myList,this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        etColumn1 = (EditText) findViewById(R.id.etColumn1);
        etColumn2 = (EditText) findViewById(R.id.etColumn2);
        etColumn3 = (EditText) findViewById(R.id.etColumn3);
        btnCreateDocument = (Button) findViewById(R.id.createTableButton);
        btnAddItem = (Button) findViewById(R.id.addRowButton);
        btnAddItem.setOnClickListener(v -> {
            word = etColumn1.getText().toString();
            definition = etColumn2.getText().toString();
            sentence = etColumn3.getText().toString();
            if (word.matches("")) {
                Toast.makeText(v.getContext(), "You did not enter a response.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (definition.matches("")) {
                Toast.makeText(v.getContext(), "You did not enter a response." , Toast.LENGTH_SHORT).show();
                return;
            }
            if (definition.matches("")) {
                Toast.makeText(v.getContext(), "You did not enter a response." , Toast.LENGTH_SHORT).show();
                return;
            }

            RecyclerData mLog = new RecyclerData();
            mLog.setWord(word);
            mLog.setDefinition(definition);
            mLog.setSentence(sentence);
            myList.add(mLog);
            mRecyclerAdapter.notifyData(myList);
           //clear the edittext boxes for new entries
            etColumn1.setText("");
            etColumn2.setText("");
            etColumn3.setText("");
        });

        Intent a = getIntent();
        String column1 = a.getStringExtra("column1");
        String column2 = a.getStringExtra("column2");
        String column3 = a.getStringExtra("column3");


        ((TextView)findViewById(R.id.etColumn1)).setHint(column1);
        ((TextView)findViewById(R.id.etColumn2)).setHint(column2);
        ((TextView)findViewById(R.id.etColumn3)).setHint(column3);
    }

    @Override
    public void OnRemoveClick(int index) {
        myList.remove(index);
        mRecyclerAdapter.notifyData(myList);
    }


    public void createPDF(View v) throws FileNotFoundException {
        Intent a = getIntent();
        String column1 = a.getStringExtra("column1");
        String column2 = a.getStringExtra("column2");
        String column3 = a.getStringExtra("column3");
        String documentName = a.getStringExtra("documentName");


        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, documentName + ".pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);



        //Set Column widths
        float[] columnWidths = {1f, 1f, 1f};
        Table table = new Table(3);
        table.setWidth(500);
        table.setRelativePosition(25f, 25f, 25f, 25f);


        Paragraph cell1 = new Paragraph(column1);
        cell1.setPaddingLeft(10);
        cell1.setBold();
        cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell1.setVerticalAlignment(VerticalAlignment.MIDDLE);


        Paragraph cell2 = new Paragraph(column2);
        cell1.setPaddingLeft(10);
        cell1.setBold();
        cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell1.setVerticalAlignment(VerticalAlignment.MIDDLE);

        Paragraph cell3 = new Paragraph(column3);
        cell1.setPaddingLeft(10);
        cell1.setBold();
        cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell1.setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);


        for(int i = 0; i < myList.size(); i++){

            String temp1 = myList.get(i).getWord();
            String temp2 = myList.get(i).getDefinition();
            String temp3 = myList.get(i).getSentence();

            table.addCell(temp1);
            table.addCell(temp2);
            table.addCell(temp3);
        }





        document.add(table);
        document.close();

        Toast.makeText(this, "PDF Created", Toast.LENGTH_LONG).show();

    }
}