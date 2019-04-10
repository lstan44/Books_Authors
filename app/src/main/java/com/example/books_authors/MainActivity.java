package com.example.books_authors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void allProducts(View v) {

        Intent i = new Intent(this, BooksAuthors.class);
        i.putExtra("query", "select categoryName, ProductName, UnitPrice from products,categories where categories.categoryID = Products.categoryID ORDER BY categoryName;");
        startActivity(i);
    }

    public void allCategories(View v){

        Intent i = new Intent(this, BooksAuthors.class);
        i.putExtra("query", "select categoryName, count(ProductID) from products, categories where categories.categoryID=Products.categoryID group by categoryName;");
        startActivity(i);
    }
}