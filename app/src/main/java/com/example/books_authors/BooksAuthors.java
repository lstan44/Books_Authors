package com.example.books_authors;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BooksAuthors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_authors);

        Intent i = getIntent();
        String q = i.getStringExtra("query");

        ArrayList<String> values = queryDB(q);

        ListView lv = findViewById(R.id.books_view);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.activity_list_item, values);
        lv.setAdapter(adp);
    }

    ArrayList<String> queryDB(String query){
        ArrayList<String> list = new ArrayList<>();

        try{
            SQLiteDatabase db = openOrCreateDatabase("foodProducts.db", Context.MODE_PRIVATE,null);
            Cursor cursor = db.rawQuery(query,null);
            while(cursor.moveToNext()){
                StringBuilder data = new StringBuilder();
                for(int i =0; i<cursor.getColumnCount(); i++){
                    if(i>0) data.append("||");
                    String colvalue = cursor.getString(i);
                    data.append(colvalue);
                }
                list.add(data.toString());
            }
            cursor.close();
            db.close();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        return  list;
    }
}
