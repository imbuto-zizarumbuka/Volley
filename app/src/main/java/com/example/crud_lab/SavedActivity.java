package com.example.crud_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SavedActivity extends AppCompatActivity {

    private DatabaseHandler dbHandler;
    private List<Contact> contacts;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;

    View.OnClickListener listener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Contact contact =(Contact) view.getTag();
            dbHandler.deleteContact(contact);

            contacts=dbHandler.getAllContact();
            contactAdapter = new ContactAdapter(contacts, listener, true);
            recyclerView.setAdapter(contactAdapter);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        dbHandler = new DatabaseHandler(this);
        contacts=dbHandler.getAllContact();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        contactAdapter = new ContactAdapter(contacts, listener, true);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(contactAdapter);

        Button button = findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SavedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}